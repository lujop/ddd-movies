package cat.joanpujol.dddmovies.imdbimport.application.common;

import java.util.List;
import javax.annotation.Nullable;
import org.immutables.value.Value;

@Value.Immutable
public interface Response<T> {
  @Value.Parameter(order = 1)
  boolean isCorrect();

  @Value.Parameter(order = 2)
  @Nullable
  T content();

  List<Message> messages();

  public static <T> ImmutableResponse.Builder<T> builder() {
    return ImmutableResponse.builder();
  }

  public static <T> Response<T> of(boolean correct, @Nullable T content) {
    return ImmutableResponse.of(correct, content);
  }

  public static <T> Response<T> of(boolean correct, @Nullable T content, Message message) {
    return ImmutableResponse.<T>builder()
        .isCorrect(correct)
        .content(content)
        .addMessages(message)
        .build();
  }

  public static <T> Response<T> of(boolean correct, @Nullable T content, String message) {
    return ImmutableResponse.<T>builder()
        .isCorrect(correct)
        .content(content)
        .addMessages(ImmutableMessage.builder().message(message).build())
        .build();
  }
}
