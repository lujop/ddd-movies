package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import java.io.InputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/")
public interface RetrieveIMDBFileHttpClient {

  @GET
  @Path("/{name}")
  InputStream getFile(RetrieveIMDBFile.Type type);
}
