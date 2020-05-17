package cat.joanpujol.dddmovies.imdbimport.infrastructure.entities.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import cat.joanpujol.dddmovies.imdbimport.domain.Title;
import cat.joanpujol.dddmovies.imdbimport.infrastructure.entities.TitleEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TitleEntityMapperTest {

  @ParameterizedTest
  @MethodSource("cat.joanpujol.dddmovies.imdbimport.domain.CreateTestDomainObjects#titles")
  public void mappingToEntityAndThenToDomainShouldPreserveValues(Title originalTitle) {
    var mapper = new TitleEntityMapper();

    TitleEntity mappendEntity = mapper.toEntity(originalTitle);
    var entityMapped2Domain = mapper.toDomain(mappendEntity);

    assertThat(entityMapped2Domain).isEqualToComparingFieldByField(originalTitle);
  }
}
