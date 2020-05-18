package cat.joanpujol.dddmovies.imdbimport.view;

import cat.joanpujol.dddmovies.imdbimport.application.common.Response;
import cat.joanpujol.dddmovies.imdbimport.application.updater.titlebasics.UpdateTitleBasics;
import cat.joanpujol.dddmovies.imdbimport.application.updater.titlebasics.UpdateTitleRatings;
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
  @Inject private UpdateTitleRatings updateTitleRatings;

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public String update() throws IOException {
    Response<Void> titlesResponse = updateTitleBasics.update();
    Response<Void> ratingsResponse = updateTitleRatings.update();

    return titlesResponse.toString() + "\n" + ratingsResponse.toString();
  }
}
