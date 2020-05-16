package cat.joanpujol.dddmovies.imdbimport.application.retriever.parser;

public interface IMDBParser<T> {
  T parse(String line);
}
