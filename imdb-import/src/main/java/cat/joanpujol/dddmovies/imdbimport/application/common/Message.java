package cat.joanpujol.dddmovies.imdbimport.application.common;

import org.immutables.value.Value;

@Value.Immutable
public interface Message {
  enum Severity {
    ERROR,
    WARNING,
    INFO
  }

  @Value.Default
  default Severity severity() {
    return Severity.INFO;
  }

  @Value.Parameter(order = 1)
  String message();

  public static ImmutableMessage.Builder builder() {
    return ImmutableMessage.builder();
  }

  public static <T> Message of(String message) {
    return ImmutableMessage.of(message);
  }

  public static <T> Message of(String message, Severity severity) {
    return builder().message(message).severity(severity).build();
  }
}
