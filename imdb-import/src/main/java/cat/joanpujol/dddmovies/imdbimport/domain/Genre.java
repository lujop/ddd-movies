package cat.joanpujol.dddmovies.imdbimport.domain;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class Genre extends BaseValueObject {
  private final String name;

  public Genre(String name) {
    Preconditions.checkArgument(
        !Strings.isNullOrEmpty(name.trim()), "genre name must not be null or blank");
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public Object[] getEqualityValues() {
    return new Object[] {name};
  }
}
