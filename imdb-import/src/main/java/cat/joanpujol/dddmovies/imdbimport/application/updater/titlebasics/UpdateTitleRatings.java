package cat.joanpujol.dddmovies.imdbimport.application.updater.titlebasics;

import cat.joanpujol.dddmovies.imdbimport.application.common.Response;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.IMDBRetriever;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.TitleRatings;
import cat.joanpujol.dddmovies.imdbimport.domain.Id;
import cat.joanpujol.dddmovies.imdbimport.domain.Rating;
import cat.joanpujol.dddmovies.imdbimport.domain.Title;
import cat.joanpujol.dddmovies.imdbimport.domain.repository.TitleRepository;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class UpdateTitleRatings {
  private static Logger logger = LoggerFactory.getLogger(UpdateTitleRatings.class);
  private final IMDBRetriever imdbRetriever;
  private final TitleRepository titleRepository;

  @Inject
  public UpdateTitleRatings(IMDBRetriever imdbRetriever, TitleRepository titleRepository) {
    this.imdbRetriever = imdbRetriever;
    this.titleRepository = titleRepository;
  }

  public Response<Void> update() throws IOException {
    logger.info("Start TitleRatings update");
    var retrieveOperation = imdbRetriever.retrieveTitleRatings();

    AtomicInteger updated = new AtomicInteger(0);
    retrieveOperation
        .getData()
        .subscribe()
        .with(
            titleRating -> updateTitleWithRating(titleRating, updated),
            error -> logger.error("Error updating ratings", error));

    logger.info(
        "End TitleRatings update. Updated {}. Retrieved correct registers: {} Incorrect registers: {}",
        updated.get(),
        retrieveOperation.getCorrectCount(),
        retrieveOperation.getIncorrectCount());

    return Response.of(true, null, String.format("Updated %d registers", updated.get()));
  }

  private void updateTitleWithRating(TitleRatings titleRating, AtomicInteger updateCount) {
    Title title = titleRepository.findById(new Id(titleRating.id()));
    if (title != null) {
      title.setRating(new Rating(titleRating.averageRating(), titleRating.numberOfVotes()));
      titleRepository.save(title);
      logger.debug("Persisting rates for {}", title);
      updateCount.incrementAndGet();
    } else {
      logger.warn("Ignoring rating for {} because id not found", titleRating);
    }
  }
}
