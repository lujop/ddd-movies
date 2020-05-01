package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import java.io.InputStream;
import org.checkerframework.checker.nullness.NullnessUtil;

public class RetrieveIMDBFileHttpClientMock implements RetrieveIMDBFileHttpClient {
  @Override
  public InputStream getFile(RetrieveIMDBFile.Type type) {
    @SuppressWarnings("nullable")
    var resourceAsStream =
        Thread.currentThread()
            .getContextClassLoader()
            .getResourceAsStream("mockdata/retrievefile/" + type.getFileName());
    return NullnessUtil.castNonNull(resourceAsStream);
  }
}
