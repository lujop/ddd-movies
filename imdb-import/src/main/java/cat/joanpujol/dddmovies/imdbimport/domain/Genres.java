package cat.joanpujol.dddmovies.imdbimport.domain;

import java.util.*;
import java.util.stream.Collectors;

public final class Genres extends BaseValueObject {
  static final int MAX_GENRES = 3;
  private final Set<Genre> genresList = new LinkedHashSet<>();

  public Genres(Collection<Genre> genres) {
    if (genres.size() <= MAX_GENRES) {
      this.genresList.addAll(genres);
    } else {
      throw new IllegalArgumentException("Maximum number of genres is " + MAX_GENRES);
    }
  }

  public Set<Genre> getList() {
    return Collections.unmodifiableSet(genresList);
  }

  public static Genres of(String... genre) {
    var genresList = Arrays.stream(genre).map(Genre::new).collect(Collectors.toList());
    return new Genres(genresList);
  }

  @Override
  public Object[] getEqualityValues() {
    return new Object[] {genresList};
  }
}
