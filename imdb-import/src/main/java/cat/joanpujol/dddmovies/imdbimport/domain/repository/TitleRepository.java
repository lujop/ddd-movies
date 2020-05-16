package cat.joanpujol.dddmovies.imdbimport.domain.repository;

import cat.joanpujol.dddmovies.imdbimport.domain.Id;
import cat.joanpujol.dddmovies.imdbimport.domain.Title;

public interface TitleRepository {
  public Title findById(Id id);

  public void save(Title title);
}
