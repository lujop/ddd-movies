package cat.joanpujol.dddmovies.imdbimport.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RuntimeDurationTest {

  @Test
  public void negativeRuntimeDurationShouldThrowError() {
    Duration negativeDuration = Duration.of(-1, ChronoUnit.MINUTES);
    assertThatIllegalArgumentException().isThrownBy(() -> new RuntimeDuration(negativeDuration));
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1})
  public void runtimeDurationCanBeAnyNonNegativeDuration(int seconds) {
    Duration duration = Duration.of(seconds, ChronoUnit.SECONDS);
    assertThat(duration.getSeconds()).isEqualTo(seconds);
  }
}
