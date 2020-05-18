package cat.joanpujol.dddmovies.imdbimport.domain.repository;

import cat.joanpujol.dddmovies.imdbimport.domain.Id;
import cat.joanpujol.dddmovies.imdbimport.domain.Title;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface TitleRepository {
  public @Nullable Title findById(Id id);

  public void save(Title title);
}
