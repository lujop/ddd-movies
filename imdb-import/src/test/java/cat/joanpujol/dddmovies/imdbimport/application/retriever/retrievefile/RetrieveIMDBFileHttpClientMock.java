package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import cat.joanpujol.dddmovies.imdbimport.infrastructure.annotations.Mock;
import java.io.InputStream;
import org.checkerframework.checker.nullness.NullnessUtil;

@Mock
public class RetrieveIMDBFileHttpClientMock implements RetrieveIMDBFileHttpClient {
  @Override
  public InputStream getFile(String fileName) {
    @SuppressWarnings("nullable")
    var resourceAsStream =
        Thread.currentThread()
            .getContextClassLoader()
            .getResourceAsStream("mockdata/retrievefile/" + fileName);
    return NullnessUtil.castNonNull(resourceAsStream);
  }
}
