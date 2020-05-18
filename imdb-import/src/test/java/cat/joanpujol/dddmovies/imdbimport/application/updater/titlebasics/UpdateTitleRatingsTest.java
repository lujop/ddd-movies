package cat.joanpujol.dddmovies.imdbimport.application.updater.titlebasics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import cat.joanpujol.dddmovies.imdbimport.application.retriever.IMDBRetriever;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile.RetrieveIMDBFileHttpClientMock;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile.RetrieveIMDBFileImpl;
import cat.joanpujol.dddmovies.imdbimport.domain.CreateTestDomainObjects;
import cat.joanpujol.dddmovies.imdbimport.domain.Id;
import cat.joanpujol.dddmovies.imdbimport.domain.repository.TitleRepository;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UpdateTitleRatingsTest {
  public static final int NUMBER_OF_REGISTERS = 2;
  private UpdateTitleRatings instance;
  private TitleRepository titleRepository;

  @BeforeEach
  private void setup() {
    RetrieveIMDBFileImpl retrieveFileMocked =
        new RetrieveIMDBFileImpl(new RetrieveIMDBFileHttpClientMock());
    var retriever = new IMDBRetriever(retrieveFileMocked, NUMBER_OF_REGISTERS);
    titleRepository = Mockito.mock(TitleRepository.class);
    instance = new UpdateTitleRatings(retriever, titleRepository);

    // Mock will only found 1 of NUMBER_OF_REGISTERS
    when(titleRepository.findById(new Id("tt0000001")))
        .thenReturn(CreateTestDomainObjects.createSampleTitleWithAllData());
  }

  @AfterEach
  private void tearDown() {
    instance = null;
  }

  @Test
  public void updateIsSuccesfulAndPersistData() throws IOException {
    var result = instance.update();

    verify(titleRepository, times(NUMBER_OF_REGISTERS)).findById(any());
    // Save is only called for found register
    verify(titleRepository, times(1)).save(any());
    assertThat(result.isCorrect()).isTrue();
  }

  @Test
  public void messageContainsNumberOfUpdatedRegisters() throws IOException {
    var result = instance.update();

    assertThat(result.messages().get(0).message())
        .contains("Updated 1 registers"); // Only one found in repository
  }
}
