package cat.joanpujol.dddmovies.imdbimport.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.Duration;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TitleTest {
  private Title pulpFiction;

  @BeforeEach
  public void setup() {
    pulpFiction =
        new Title(
            new Id("t1"),
            new TitleName("Pulp Fiction"),
            new TitleName("Pulp Fiction"),
            Adult.ADULT,
            Year.of(1994),
            new RuntimeDuration(Duration.of(154, ChronoUnit.MINUTES)),
            Genres.of("Crime", "Drama"));
  }

  @AfterEach
  public void tearDown() {
    pulpFiction = null;
  }

  @Test
  public void titleCanBeCreated() {
    assertThat(pulpFiction.getId()).isEqualTo(new Id("t1"));
  }

  @ParameterizedTest
  @ValueSource(ints = {1994, 1995})
  public void endYearCantBeSet(int year) {
    pulpFiction.setEndYear(Year.of(year));

    assertThat(pulpFiction.getEndYear()).isEqualTo(Year.of(year));
  }

  @Test
  public void endYearCantBeSetNull() {
    pulpFiction.setEndYear(null);

    assertThat(pulpFiction.getEndYear()).isNull();
  }

  @Test
  public void endYearCantBeBeforeStartYear() {
    assertThatIllegalArgumentException().isThrownBy(() -> pulpFiction.setEndYear(Year.of(1990)));
  }

  @Test
  public void startYearCantBeSetAfterEndYear() {
    pulpFiction.setEndYear(Year.of(1995));

    assertThatIllegalArgumentException().isThrownBy(() -> pulpFiction.setStartYear(Year.of(1996)));
  }

  @Test
  public void startYearCantBeSetToAnyYearIfEndYearIfNull() {
    pulpFiction.setEndYear(null);

    pulpFiction.setStartYear(Year.of(3000));
  }
}
