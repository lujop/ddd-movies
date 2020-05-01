package cat.joanpujol.dddmovies.imdbimport.domain;

public class Id extends BaseValueObject {
  private final String id;

  public Id(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public Object[] getEqualityValues() {
    return new Object[] {id};
  }
}
