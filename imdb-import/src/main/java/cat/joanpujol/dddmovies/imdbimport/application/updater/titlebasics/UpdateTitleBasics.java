package cat.joanpujol.dddmovies.imdbimport.application.updater.titlebasics;

import cat.joanpujol.dddmovies.imdbimport.application.common.Response;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.IMDBRetriever;
import java.io.IOException;
import org.checkerframework.checker.nullness.qual.Nullable;

public class UpdateTitleBasics {
  private final IMDBRetriever imdbRetriever;

  public UpdateTitleBasics(IMDBRetriever imdbRetriever) {
    this.imdbRetriever = imdbRetriever;
  }

  @Nullable
  Response<Void> update(UpdateTitleBasicsRequest request) throws IOException {
    var stream = imdbRetriever.retrieveTitleBasics();
    return null;
  }
}
