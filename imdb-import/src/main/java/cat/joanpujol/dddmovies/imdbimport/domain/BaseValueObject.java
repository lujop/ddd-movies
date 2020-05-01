package cat.joanpujol.dddmovies.imdbimport.domain;

import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;

/** Base class for all ValueObjects that are not enums */
public abstract class BaseValueObject implements ValueObject {
  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(@Nullable Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    return Arrays.equals(getEqualityValues(), ((BaseValueObject) other).getEqualityValues());
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(getEqualityValues());
  }

  public abstract Object[] getEqualityValues();
}
