package cat.joanpujol.dddmovies.imdbimport.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TitleNameTest {

  @ParameterizedTest
  @MethodSource("blankStrings")
  public void shouldThrowExceptionOnBlankName(String title) {
    assertThatIllegalArgumentException().isThrownBy(() -> new TitleName(title));
  }

  private static Stream<String> blankStrings() {
    return Stream.of("", "   ");
  }
}
