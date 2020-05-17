package cat.joanpujol.dddmovies.imdbimport.application.updater.titlebasics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import cat.joanpujol.dddmovies.imdbimport.application.retriever.IMDBRetriever;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.mappers.TitleBasicDomainMapper;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile.RetrieveIMDBFileHttpClientMock;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.retrievefile.RetrieveIMDBFileImpl;
import cat.joanpujol.dddmovies.imdbimport.domain.repository.TitleRepository;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UpdateTitleBasicsTest {
  public static final int NUMBER_OF_REGISTERS = 3;
  private UpdateTitleBasics instance;
  private TitleRepository titleRepository;

  @BeforeEach
  private void setup() {
    RetrieveIMDBFileImpl retrieveFileMocked =
        new RetrieveIMDBFileImpl(new RetrieveIMDBFileHttpClientMock());
    var retriever = new IMDBRetriever(retrieveFileMocked, NUMBER_OF_REGISTERS);
    titleRepository = Mockito.mock(TitleRepository.class);
    instance = new UpdateTitleBasics(retriever, titleRepository, new TitleBasicDomainMapper());
  }

  @AfterEach
  private void tearDown() {
    instance = null;
  }

  @Test
  public void updateIsSuccesfullAndPersistData() throws IOException {
    var result = instance.update(ImmutableUpdateTitleBasicsRequest.builder().build());

    verify(titleRepository, times(NUMBER_OF_REGISTERS)).save(any());
    assertThat(result.isCorrect()).isTrue();
  }

  @Test
  public void messageContainsNumberOfUpdatedRegisters() throws IOException {
    var result = instance.update(ImmutableUpdateTitleBasicsRequest.builder().build());
    assertThat(result.messages().get(0).message())
        .contains("Updated " + NUMBER_OF_REGISTERS + " registers");
  }
}
