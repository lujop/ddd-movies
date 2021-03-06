package cat.joanpujol.dddmovies.imdbimport.infrastructure.repository;

import cat.joanpujol.dddmovies.imdbimport.domain.Id;
import cat.joanpujol.dddmovies.imdbimport.domain.Title;
import cat.joanpujol.dddmovies.imdbimport.domain.repository.TitleRepository;
import cat.joanpujol.dddmovies.imdbimport.infrastructure.entities.TitleEntity;
import cat.joanpujol.dddmovies.imdbimport.infrastructure.entities.mappers.TitleEntityMapper;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.checkerframework.checker.nullness.qual.Nullable;

@ApplicationScoped
public class TitleMongoRepository
    implements TitleRepository, PanacheMongoRepositoryBase<TitleEntity, String> {
  private final TitleEntityMapper titleEntityMapper;

  @Inject
  public TitleMongoRepository(TitleEntityMapper titleEntityMapper) {
    this.titleEntityMapper = titleEntityMapper;
  }

  @Override
  public @Nullable Title findById(Id id) {
    TitleEntity entity = findById(id.getValue());
    return titleEntityMapper.toDomain(entity);
  }

  @Override
  public void save(Title title) {
    TitleEntity entity = titleEntityMapper.toEntity(title);
    persistOrUpdate(entity);
  }
}
