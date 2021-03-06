package cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.*;

@DisplayName("Test IMDB files retrieving")
class RetrieveIMDBFileImplTest {
  private RetrieveIMDBFileImpl instance;

  @BeforeEach
  private void setup() {
    var httpClient = new RetrieveIMDBFileHttpClientMock();
    instance = new RetrieveIMDBFileImpl(httpClient);
  }

  @AfterEach
  private void tearDown() {
    instance = null;
  }

  @DisplayName("Given main TITLE_BASICS file type")
  @Nested
  class TitleBasics {
    @Test
    public void testSomeContentIsReturned() throws IOException {
      var content = instance.retrieveFile(RetrieveIMDBFile.Type.TITLE_BASICS);
      assertThat(content.collectItems().asList().await().indefinitely())
          .isNotEmpty()
          .as("Retrieved file must have lines");
    }
  }
}
