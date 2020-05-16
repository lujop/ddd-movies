package cat.joanpujol.dddmovies.imdbimport.domain;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.Nullable;

/** Base class for all entities */
public class BaseEntity {
  private final Id id;

  public BaseEntity(Id id) {
    this.id = id;
  }

  public Id getId() {
    return id;
  }

  @Override
  public boolean equals(@Nullable Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseEntity that = (BaseEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", getId()).toString();
  }
}
