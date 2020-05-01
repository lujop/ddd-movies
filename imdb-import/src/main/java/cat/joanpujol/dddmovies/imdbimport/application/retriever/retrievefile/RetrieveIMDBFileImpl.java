package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
// @Default
public class RetrieveIMDBFileImpl implements RetrieveIMDBFile {
  private final RetrieveIMDBFileHttpClient httpClient;

  @Inject
  @RestClient
  public RetrieveIMDBFileImpl(@RestClient RetrieveIMDBFileHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @Override
  public Stream<String> retrieveFile(Type type) throws IOException {
    InputStream stream = httpClient.getFile(type);
    var uncompressedStream = new GZIPInputStream(stream);
    return new BufferedReader(new InputStreamReader(uncompressedStream, StandardCharsets.UTF_8))
        .lines();
  }
}
