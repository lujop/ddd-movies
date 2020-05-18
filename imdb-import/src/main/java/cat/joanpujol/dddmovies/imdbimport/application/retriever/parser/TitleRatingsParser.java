package cat.joanpujol.dddmovies.imdbimport.application.retriever.parser;

import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.ImmutableTitleRatings;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.TitleRatings;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.parser.IMDBLineParser.ParsedLine;

/** Parse IMDB title.basic line register */
public class TitleRatingsParser implements IMDBParser<TitleRatings> {

  @Override
  public TitleRatings parse(String line) {
    ParsedLine parsedLine = IMDBLineParser.parse(line, 3);
    return ImmutableTitleRatings.builder()
        .id(parsedLine.getString(0))
        .averageRating(parsedLine.getDouble(1))
        .numberOfVotes(parsedLine.getInteger(2))
        .build();
  }
}
