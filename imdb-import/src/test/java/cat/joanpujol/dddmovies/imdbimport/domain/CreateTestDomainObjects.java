package cat.joanpujol.dddmovies.imdbimport.domain;

import java.time.Duration;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

public class CreateTestDomainObjects {

  public static Title createSampleTitleWithMinimumData() {
    Title title =
        new Title(
            new Id("t1"),
            new TitleName("Pulp Fiction"),
            new TitleName("Pulp Fiction"),
            Adult.ADULT,
            Year.of(1994),
            Genres.of("Crime", "Drama"));
    return title;
  }

  public static Title createSampleTitleWithAllData() {
    Title title = createSampleTitleWithMinimumData();

    title.setRuntimeDuration(new RuntimeDuration(Duration.of(154, ChronoUnit.MINUTES)));
    title.setEndYear(Year.of(1994));
    return title;
  }

  public static Stream<Title> titles() {
    return Stream.of(createSampleTitleWithAllData(), createSampleTitleWithMinimumData());
  }
}
