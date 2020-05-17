package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import java.io.InputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface RetrieveIMDBFileHttpClient {
  @GET
  @Path("/{fileName}")
  InputStream getFile(@PathParam("fileName") String fileName);
}
