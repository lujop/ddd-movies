package cat.joanpujol.dddmovies.imdbimport.application.retriever.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;

@DisplayName("Parse IMDB title.basics line register")
class TitleBasicParserTest {
  private static final String basicRegister =
      "tt0000001\tshort\tCarmencita\tCarmencita OT\t0\t1894\t\\N\t1\tDocumentary,Short";
  private static final String registerWithEndYear =
      "tt0000001\tshort\tCarmencita\tCarmencita OT\t0\t1894\t1895\t1\tDocumentary,Short";
  private TitleBasicParser instance;

  @BeforeEach
  private void setup() {
    instance = new TitleBasicParser();
  }

  @AfterEach
  private void tearDown() {
    instance = null;
  }

  @DisplayName("Given a line")
  @Nested
  class Parser {
    @Test
    public void checkBasicLineParsedValues() {
      var result = instance.parse(basicRegister);
      assertThat(result.id()).isEqualTo("tt0000001").as("check title id");
      assertThat(result.type()).isEqualTo("short").as("check title type");
      assertThat(result.primaryTitle()).isEqualTo("Carmencita").as("check primary title");
      assertThat(result.originalTitle()).isEqualTo("Carmencita OT").as("check original title");
      assertThat(result.adult()).isFalse().as("check title adult");
      assertThat(result.startYear()).isEqualTo(1894).as("check title start year");
      assertThat(result.endYear()).as("check title end year").isNull();
      assertThat(result.runtimeMinutes()).isEqualTo(1).as("check title runtime minutes");
      assertThat(result.genres()).containsExactly("Documentary", "Short");
    }

    @Test
    public void checkLineWithEndYearParsedValues() {
      var result = instance.parse(registerWithEndYear);
      assertThat(result.endYear()).isEqualTo(1895).as("check title end year");
    }
  }
}
