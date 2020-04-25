package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@ApplicationScoped
@Default
public class RetrieveIMDBFileImpl implements RetrieveIMDBFile {
  private final RetrieveIMDBFileHttpClient httpClient;

  @Inject
  public RetrieveIMDBFileImpl(RetrieveIMDBFileHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @Override
  public @NonNull Stream<String> retrieveFile(@NonNull Type type) throws IOException {
    InputStream stream = httpClient.getFile(type);
    var uncompressedStream = new GZIPInputStream(stream);
    return new BufferedReader(new InputStreamReader(uncompressedStream, StandardCharsets.UTF_8))
        .lines();
  }
}
