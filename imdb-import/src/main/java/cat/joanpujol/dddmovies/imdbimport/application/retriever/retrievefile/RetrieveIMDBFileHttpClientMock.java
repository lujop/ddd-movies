package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import cat.joanpujol.dddmovies.imdbimport.infrastructure.annotations.Mock;
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
