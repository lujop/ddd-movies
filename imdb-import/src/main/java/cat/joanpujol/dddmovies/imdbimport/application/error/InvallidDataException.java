package cat.joanpujol.dddmovies.imdbimport.application.error;

import edu.umd.cs.findbugs.annotations.NonNull;

public class InvallidDataException extends RuntimeException {

  public InvallidDataException(@NonNull String message) {
    super(message);
  }

  public InvallidDataException(@NonNull String message, Throwable cause) {
    super(message, cause);
  }
}
