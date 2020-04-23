package cat.joanpujol.dddmovies.imdbimport.application.retrievefile.impl;

import cat.joanpujol.dddmovies.imdbimport.application.retrievefile.RetrieveIMDBFile;
import cat.joanpujol.dddmovies.imdbimport.infrastructure.Mock;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.InputStream;
import java.util.Objects;

@Mock
public class RetrieveIMDBFileHttpClientMock implements RetrieveIMDBFileHttpClient {
  @Override
  public @NonNull InputStream getFile(@NonNull RetrieveIMDBFile.Type type) {
    return Objects.requireNonNull(
        Thread.currentThread()
            .getContextClassLoader()
            .getResourceAsStream("mockdata/retrievefile/" + type.getFileName()));
  }
}
