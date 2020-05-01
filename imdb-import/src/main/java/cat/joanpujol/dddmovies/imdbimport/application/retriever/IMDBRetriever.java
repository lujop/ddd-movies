package cat.joanpujol.dddmovies.imdbimport.application.retriever;

import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.TitleBasic;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.parser.TitleBasicParser;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile.RetrieveIMDBFile;
import java.io.IOException;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class IMDBRetriever {
  private final RetrieveIMDBFile retrieveFile;

  @Inject
  public IMDBRetriever(RetrieveIMDBFile retrieveFile) {
    this.retrieveFile = retrieveFile;
  }

  public Stream<TitleBasic> retrieveTitleBasics() throws IOException {
    Stream<String> lineStream =
        retrieveFile
            .retrieveFile(RetrieveIMDBFile.Type.TITLE_BASICS)
            .skip(1); // Ignore file header;
    TitleBasicParser parser = new TitleBasicParser();
    return lineStream.map(parser::parse);
  }
}
