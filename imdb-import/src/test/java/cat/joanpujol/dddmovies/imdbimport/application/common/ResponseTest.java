package cat.joanpujol.dddmovies.imdbimport.application.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResponseTest {

  @Test
  public void correctResponseWithStringMessage() {
    String responseContent = "A response";
    String responseMessage = "Response message";
    Response<String> response = Response.of(true, responseContent, responseMessage);

    assertThat(response.isCorrect()).isTrue();
    assertThat(response.content()).isEqualTo(responseContent);
    assertThat(response.messages()).containsExactly(Message.of(responseMessage));
  }

  @Test
  public void correctResponseWithAlreadyBuildMessage() {
    String responseContent = "A response";
    Message responseMessage = Message.of("Response message", Message.Severity.ERROR);
    Response<String> response = Response.of(true, responseContent, responseMessage);

    assertThat(response.isCorrect()).isTrue();
    assertThat(response.content()).isEqualTo(responseContent);
    assertThat(response.messages()).containsExactly(responseMessage);
  }
}
