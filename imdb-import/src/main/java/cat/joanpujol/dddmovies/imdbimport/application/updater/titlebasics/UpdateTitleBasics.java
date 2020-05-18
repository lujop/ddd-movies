package cat.joanpujol.dddmovies.imdbimport.application.updater.titlebasics;

import cat.joanpujol.dddmovies.imdbimport.application.common.Response;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.IMDBRetriever;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.mappers.TitleBasicDomainMapper;
import cat.joanpujol.dddmovies.imdbimport.domain.Title;
import cat.joanpujol.dddmovies.imdbimport.domain.repository.TitleRepository;
import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class UpdateTitleBasics {
  private static Logger logger = LoggerFactory.getLogger(UpdateTitleBasics.class);
  private final IMDBRetriever imdbRetriever;
  private final TitleRepository titleRepository;
  private final TitleBasicDomainMapper titleBasicMapper;

  @Inject
  public UpdateTitleBasics(
      IMDBRetriever imdbRetriever, TitleRepository titleRepository, TitleBasicDomainMapper mapper) {
    this.imdbRetriever = imdbRetriever;
    this.titleRepository = titleRepository;
    this.titleBasicMapper = mapper;
  }

  public Response<Void> update() throws IOException {
    logger.info("Start TitleBasics update");
    var retrieveOperation = imdbRetriever.retrieveTitleBasics();
    retrieveOperation
        .getData()
        .map(titleBasicMapper::toDomain)
        .subscribe()
        .with(this::persistTitle);

    logger.info(
        "End TitleBasics update. Correct registers: {} Incorrect registers: {}",
        retrieveOperation.getCorrectCount(),
        retrieveOperation.getIncorrectCount());

    return Response.of(
        true, null, String.format("Updated %d registers", retrieveOperation.getCorrectCount()));
  }

  private void persistTitle(Title title) {
    logger.debug("Persisting {}", title);
    titleRepository.save(title);
  }
}
