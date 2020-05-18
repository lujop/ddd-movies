package cat.joanpujol.dddmovies.imdbimport.application.retriever.beans;

import org.immutables.value.Value;

@Value.Immutable
public interface TitleRatings {
  String id();

  double averageRating();

  int numberOfVotes();
}
