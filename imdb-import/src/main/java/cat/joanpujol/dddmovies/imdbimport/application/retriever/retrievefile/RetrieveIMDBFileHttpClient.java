package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.InputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/")
public interface RetrieveIMDBFileHttpClient {

  @GET
  @Path("/{name}")
  @NonNull
  InputStream getFile(@NonNull RetrieveIMDBFile.Type type);
}
