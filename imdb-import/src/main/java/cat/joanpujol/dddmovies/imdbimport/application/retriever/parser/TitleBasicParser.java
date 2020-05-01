package cat.joanpujol.dddmovies.imdbimport.application.retriever.parser;

import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.ImmutableTitleBasic;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.TitleBasic;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.parser.IMDBLineParser.ParsedLine;

/** Parse IMDB title.basic line register */
public class TitleBasicParser {

  public TitleBasic parse(String line) {
    ParsedLine parsedLine = IMDBLineParser.parse(line, 9);

    return ImmutableTitleBasic.builder()
        .id(parsedLine.getString(0))
        .type(parsedLine.getString(1))
        .primaryTitle(parsedLine.getString(2))
        .originalTitle(parsedLine.getString(3))
        .adult(parsedLine.getBoolean(4))
        .startYear(parsedLine.getInteger(5))
        .endYear(parsedLine.getNullableInteger(6))
        .runtimeMinutes(parsedLine.getInteger(7))
        .genres(parsedLine.getStringCommaSeparatedSet(8))
        .build();
  }
}
