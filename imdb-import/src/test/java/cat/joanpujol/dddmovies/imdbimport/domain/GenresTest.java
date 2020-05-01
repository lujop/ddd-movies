package cat.joanpujol.dddmovies.imdbimport.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class GenresTest {

  @Test
  public void shouldBeAbleToAddThreeGenres() {
    Genres genres = Genres.of("genre1", "genre2", "genre3");
    assertThat(genres.getList()).hasSize(3);
  }

  @Test
  public void cantHaveMoreThanThreeGenres() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> Genres.of("genre1", "genre2", "genre3", "genre4"));
  }
}
