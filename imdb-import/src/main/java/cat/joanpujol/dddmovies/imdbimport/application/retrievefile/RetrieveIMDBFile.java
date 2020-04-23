package cat.joanpujol.dddmovies.imdbimport.application.retrievefile;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.IOException;
import java.util.stream.Stream;

public interface RetrieveIMDBFile {
  /** Returns a line stream with the content of the given file type */
  @NonNull
  Stream<String> retrieveFile(@NonNull Type type) throws IOException;

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

    Type(@NonNull String fileName) {
      this.fileName = fileName;
    }

    public @NonNull String getFileName() {
      return fileName;
    }
  }
}
