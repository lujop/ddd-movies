package cat.joanpujol.dddmovies.imdbimport.application.retriever;

import static org.assertj.core.api.Assertions.assertThat;

import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.ImmutableTitleBasic;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.TitleBasic;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile.RetrieveIMDBFileHttpClientMock;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile.RetrieveIMDBFileImpl;
import java.io.IOException;
import java.util.Collections;
import org.junit.jupiter.api.*;

@DisplayName("Test IMDB retriever")
class IMDBRetrieverTest {
  private IMDBRetriever retriever;

  @BeforeEach
  private void setup() {
    RetrieveIMDBFileImpl retrieveFileMocked =
        new RetrieveIMDBFileImpl(new RetrieveIMDBFileHttpClientMock());
    retriever = new IMDBRetriever(retrieveFileMocked);
  }

  @AfterEach
  void tearDown() {
    retriever = null;
  }

  @DisplayName("Title basic retrieving")
  @Nested
  class TitleBasics {
    @Test
    void retrieveTitleBasics() throws IOException {
      var retrievedStream = retriever.retrieveTitleBasics();
      assertThat(retrievedStream)
          .usingElementComparatorOnFields("id")
          .containsExactly(
              createTitleBasicsWithId("tt0000001"),
              createTitleBasicsWithId("tt0000002"),
              createTitleBasicsWithId("tt0000003"),
              createTitleBasicsWithId("tt0000004"),
              createTitleBasicsWithId("tt0000005"));
    }
  }

  private TitleBasic createTitleBasicsWithId(String code) {
    return ImmutableTitleBasic.builder()
        .id(code)
        .type("type")
        .primaryTitle("primary title")
        .originalTitle("original title")
        .adult(false)
        .startYear(0)
        .endYear(0)
        .runtimeMinutes(100)
        .genres(Collections.emptyList())
        .build();
  }
}
