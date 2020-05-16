package cat.joanpujol.dddmovies.imdbimport.view;

import cat.joanpujol.dddmovies.imdbimport.application.common.Response;
import cat.joanpujol.dddmovies.imdbimport.application.updater.titlebasics.ImmutableUpdateTitleBasicsRequest;
import cat.joanpujol.dddmovies.imdbimport.application.updater.titlebasics.UpdateTitleBasics;
import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/update")
@ApplicationScoped
public class UpdateController {
  @Inject private UpdateTitleBasics updateTitleBasics;

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public String update() throws IOException {
    Response<Void> response =
        updateTitleBasics.update(ImmutableUpdateTitleBasicsRequest.builder().build());
    return response.toString();
  }
}
