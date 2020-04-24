package cat.joanpujol.dddmovies.imdbimport.application.retriever;

import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.TitleBasic;
import java.util.stream.Stream;

public interface IMDBRetriever {
  public Stream<TitleBasic> retrieveTitleBasics();
}
