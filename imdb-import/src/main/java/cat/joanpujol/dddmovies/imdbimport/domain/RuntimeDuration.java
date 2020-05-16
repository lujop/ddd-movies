package cat.joanpujol.dddmovies.imdbimport.domain;

import com.google.common.base.Preconditions;
import java.time.Duration;

public class RuntimeDuration extends BaseValueObject {
  private final Duration duration;

  public RuntimeDuration(Duration duration) {
    Preconditions.checkArgument(duration.getSeconds() > 0, "Runtime duration must be positive");
    this.duration = duration;
  }

  public Duration getDuration() {
    return duration;
  }

  @Override
  public Object[] getEqualityValues() {
    return new Object[] {duration};
  }
}
