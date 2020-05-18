package cat.joanpujol.dddmovies.imdbimport.infrastructure.entities.mappers;

import cat.joanpujol.dddmovies.imdbimport.domain.*;
import cat.joanpujol.dddmovies.imdbimport.infrastructure.entities.TitleEntity;
import java.time.Duration;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TitleEntityMapper {
  public TitleEntity toEntity(Title title) {
    TitleEntity entity = new TitleEntity();
    entity.setId(title.getId().getValue());
    entity.setMainTitle(title.getMainTitle().getName());
    entity.setOriginalTitle(title.getOriginalTitle().getName());
    entity.setAdult(title.getAdult() == Adult.ADULT);
    entity.setStartYear(title.getStartYear().getValue());
    Year endYear = title.getEndYear();
    if (endYear != null) {
      entity.setEndYear(endYear.getValue());
    }
    RuntimeDuration runtimeDuration = title.getRuntimeDuration();
    if (runtimeDuration != null) {
      entity.setRuntimeDuration((int) runtimeDuration.getDuration().toMinutes());
    }
    entity.setGenres(genresToEntity(title.getGenres()));
    entity.setNumberOfVotes(title.getRating().getNumberOfVotes());
    entity.setAverageRating(title.getRating().getAverageRating());
    return entity;
  }

  private List<String> genresToEntity(Genres genres) {
    return genres.getList().stream().map(Genre::getName).collect(Collectors.toList());
  }

  public Title toDomain(TitleEntity entity) {
    Title title =
        new Title(
            new Id(entity.getId()),
            new TitleName(entity.getMainTitle()),
            new TitleName(entity.getOriginalTitle()),
            entity.isAdult() ? Adult.ADULT : Adult.NON_ADULT,
            Year.of(entity.getStartYear()),
            Genres.of(entity.getGenres().toArray(new String[0])));
    Integer endYear = entity.getEndYear();
    if (endYear != null) {
      title.setEndYear(Year.of(endYear));
    }
    Integer runtimeDuration = entity.getRuntimeDuration();
    if (runtimeDuration != null) {
      title.setRuntimeDuration(
          new RuntimeDuration(Duration.of(runtimeDuration, ChronoUnit.MINUTES)));
    }
    title.setRating(new Rating(entity.getAverageRating(), entity.getNumberOfVotes()));
    return title;
  }
}
