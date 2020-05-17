package cat.joanpujol.dddmovies.imdbimport.infrastructure.entities.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import cat.joanpujol.dddmovies.imdbimport.domain.Title;
import cat.joanpujol.dddmovies.imdbimport.domain.TitleTest;
import cat.joanpujol.dddmovies.imdbimport.infrastructure.entities.TitleEntity;
import org.junit.jupiter.api.Test;

class TitleEntityMapperTest {

  @Test
  public void mappingToEntityAndThenToDomainShouldPreserveValues() {
    Title originalTitle = TitleTest.createSampleTitle();
    var mapper = new TitleEntityMapper();

    TitleEntity mappendEntity = mapper.toEntity(originalTitle);
    var entityMapped2Domain = mapper.toDomain(mappendEntity);

    assertThat(entityMapped2Domain).isEqualToComparingFieldByField(originalTitle);
  }
}
