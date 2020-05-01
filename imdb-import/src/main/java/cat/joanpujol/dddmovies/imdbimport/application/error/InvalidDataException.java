package cat.joanpujol.dddmovies.imdbimport.application.error;

public class InvalidDataException extends RuntimeException {

  public InvalidDataException(String message) {
    super(message);
  }

  public InvalidDataException(String message, Throwable cause) {
    super(message, cause);
  }
}
