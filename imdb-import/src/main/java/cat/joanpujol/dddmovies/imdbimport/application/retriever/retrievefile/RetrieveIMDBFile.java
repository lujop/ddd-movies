package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import io.smallrye.mutiny.Multi;
import java.io.IOException;

public interface RetrieveIMDBFile {
  /** Returns a line stream with the content of the given file type */
  Multi<String> retrieveFile(Type type) throws IOException;

  /** Types of data files provided by IMDB (https://www.imdb.com/interfaces/) */
  enum Type {
    /** Master table titles (movies, series) */
    TITLE_BASICS("title.basics.tsv.gz"),
    /** Alternative names for titles i.e. different languages */
    TITLE_AKAS("title.akas.tsv.gz"),
    /** Director and writer data */
    TITLE_CREW("title.crew.tsv.gz"),
    /** Parent-child relationship for tv series episodes */
    TITLE_EPISODE("title.episode.tsv.gz"),
    /** Cast/Crew data */
    TITLE_PRINCIPALS("title.principals.tsv.gz "),
    /** Ratings and votes */
    TITLE_RATINGS("title.ratings.tsv.gz"),
    /** Peoples name and other personal data */
    NAME_BASICS("name.basics.tsv.gz ");

    private final String fileName;

    Type(String fileName) {
      this.fileName = fileName;
    }

    public String getFileName() {
      return fileName;
    }
  }
}
