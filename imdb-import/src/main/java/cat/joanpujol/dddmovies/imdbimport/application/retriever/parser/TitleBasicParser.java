package cat.joanpujol.dddmovies.imdbimport.application.retriever.parser;

import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.ImmutableTitleBasic;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.TitleBasic;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.parser.IMDBLineParser.ParsedLine;
import java.util.Set;

/** Parse IMDB title.basic line register */
public class TitleBasicParser implements IMDBParser<TitleBasic> {

  @Override
  public TitleBasic parse(String line) {
    ParsedLine parsedLine = IMDBLineParser.parse(line, 9);

    Set<String> genresString = parsedLine.getStringCommaSeparatedSet(8);
    Integer runtimeMinutes = parsedLine.getNullableInteger(7);
    if (runtimeMinutes != null && runtimeMinutes == 0) { // Zero is considered as no value
      runtimeMinutes = null;
    }
    return ImmutableTitleBasic.builder()
        .id(parsedLine.getString(0))
        .type(parsedLine.getString(1))
        .primaryTitle(parsedLine.getString(2))
        .originalTitle(parsedLine.getString(3))
        .adult(parsedLine.getBoolean(4))
        .startYear(parsedLine.getInteger(5))
        .endYear(parsedLine.getNullableInteger(6))
        .runtimeMinutes(runtimeMinutes)
        .genres(genresString)
        .build();
  }
}
