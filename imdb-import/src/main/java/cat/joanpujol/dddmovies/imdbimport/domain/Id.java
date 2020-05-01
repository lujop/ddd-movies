package cat.joanpujol.dddmovies.imdbimport.domain;

public class Id extends BaseValueObject {
  private final String value;

  public Id(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public Object[] getEqualityValues() {
    return new Object[] {value};
  }
}
