package cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.mappers;

import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.TitleBasic;
import cat.joanpujol.dddmovies.imdbimport.domain.*;
import java.time.Duration;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TitleBasicDomainMapper {

  public Title toDomain(TitleBasic titleBasic) {
    Title title =
        new Title(
            new Id(titleBasic.id()),
            new TitleName(titleBasic.primaryTitle()),
            new TitleName(titleBasic.originalTitle()),
            titleBasic.adult() ? Adult.ADULT : Adult.NON_ADULT,
            Year.of(titleBasic.startYear()),
            Genres.of(titleBasic.genres().toArray(new String[0])));
    Integer endYear = titleBasic.endYear();
    if (endYear != null) {
      title.setEndYear(Year.of(endYear));
    }
    Integer runtimeDuration = titleBasic.runtimeMinutes();
    if (runtimeDuration != null) {
      title.setRuntimeDuration(
          new RuntimeDuration(Duration.of(runtimeDuration, ChronoUnit.MINUTES)));
    }
    return title;
  }
}
