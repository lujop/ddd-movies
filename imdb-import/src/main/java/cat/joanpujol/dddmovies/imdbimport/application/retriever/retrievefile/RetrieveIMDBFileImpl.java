package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import io.smallrye.mutiny.Multi;
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
public class RetrieveIMDBFileImpl implements RetrieveIMDBFile {
  private final RetrieveIMDBFileHttpClient httpClient;

  @Inject
  public RetrieveIMDBFileImpl(@RestClient RetrieveIMDBFileHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @Override
  public Multi<String> retrieveFile(Type type) throws IOException {
    InputStream inputStream = httpClient.getFile(type.getFileName());
    var uncompressedStream = new GZIPInputStream(inputStream);
    Stream<String> lineStream =
        new BufferedReader(new InputStreamReader(uncompressedStream, StandardCharsets.UTF_8))
            .lines();
    return Multi.createFrom().items(lineStream);
  }
}
