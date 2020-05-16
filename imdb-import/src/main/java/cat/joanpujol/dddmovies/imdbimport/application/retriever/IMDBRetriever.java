package cat.joanpujol.dddmovies.imdbimport.application.retriever;

import cat.joanpujol.dddmovies.imdbimport.application.error.InvalidDataException;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.TitleBasic;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.parser.IMDBParser;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.parser.TitleBasicParser;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile.RetrieveIMDBFile;
import io.smallrye.mutiny.Multi;
import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.checkerframework.checker.initialization.qual.UnderInitialization;
import org.checkerframework.checker.nullness.NullnessUtil;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class IMDBRetriever {
  private static final Logger logger = LoggerFactory.getLogger(IMDBRetriever.class);

  private final RetrieveIMDBFile retrieveFile;
  private final int maxNumberOfRegisters;

  @Inject
  public IMDBRetriever(
      RetrieveIMDBFile retrieveFile,
      @ConfigProperty(name = "imdbimport.retriever.maxRegisters") int maxNumberOfRegisters) {
    this.retrieveFile = retrieveFile;
    this.maxNumberOfRegisters = maxNumberOfRegisters;
  }

  public IMDBRetrieveOperation<TitleBasic> retrieveTitleBasics() throws IOException {
    Multi<String> lineStream =
        retrieveFile
            .retrieveFile(RetrieveIMDBFile.Type.TITLE_BASICS)
            .transform()
            .bySkippingFirstItems(1); // Ignore file header

    if (maxNumberOfRegisters != -1) {
      logger.info("Taking only first {} registers", maxNumberOfRegisters);
      lineStream = lineStream.transform().byTakingFirstItems(maxNumberOfRegisters);
    }

    TitleBasicParser parser = new TitleBasicParser();
    return new IMDBRetrieveOperation<TitleBasic>(lineStream, parser);
  }

  public static final class IMDBRetrieveOperation<T> {
    private final Multi<T> data;
    private int correctCount;
    private int incorrectCount;

    public IMDBRetrieveOperation(Multi<String> lineStream, IMDBParser<T> parser) {
      data =
          lineStream
              .map(line -> parse(line, parser))
              .transform()
              .byFilteringItemsWith(this::filterIncorrect)
              .map(r -> NullnessUtil.castNonNull(r.getResult()));
    }

    private final boolean filterIncorrect(
        @UnderInitialization(Object.class) IMDBRetrieveOperation<T> this,
        ParsedResult parsedResult) {
      boolean correct = parsedResult.isCorrect();
      if (correct) {
        correctCount++;
      } else {
        incorrectCount++;
      }
      return correct;
    }

    public Multi<T> getData() {
      return data;
    }

    public int getCorrectCount() {
      return correctCount;
    }

    public int getIncorrectCount() {
      return incorrectCount;
    }

    private static <T> ParsedResult<T> parse(String line, IMDBParser<T> parser) {
      try {
        return new ParsedResult<>(true, parser.parse(line), line);
      } catch (InvalidDataException e) {
        logger.debug("Error parsing line {}: {}", line, e.getMessage());
        return new ParsedResult<>(false, null, line);
      }
    }
  }
}

class ParsedResult<T> {
  private final boolean correct;
  private final @Nullable T result;
  private final String parsedLine;

  public ParsedResult(boolean correct, @Nullable T result, String parsedLine) {
    this.correct = correct;
    this.result = result;
    this.parsedLine = parsedLine;
  }

  public boolean isCorrect() {
    return correct;
  }

  public @Nullable T getResult() {
    return result;
  }

  public String getParsedLine() {
    return parsedLine;
  }
}
