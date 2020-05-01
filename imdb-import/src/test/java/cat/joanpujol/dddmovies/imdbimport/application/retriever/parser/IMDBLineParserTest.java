package cat.joanpujol.dddmovies.imdbimport.application.retriever.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import cat.joanpujol.dddmovies.imdbimport.application.error.InvalidDataException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IMDBLineParserTest {
  private static final String line = "string\t12\ttrue\t\\N\t\ta";
  private IMDBLineParser.ParsedLine parsedLine;

  @BeforeEach
  private void setup() {
    parsedLine = IMDBLineParser.parse(line);
  }

  @AfterEach
  private void tearDown() {
    parsedLine = null;
  }

  @Test
  public void errorIsThrowIfExpectedColumnsDoesntMatch() {
    assertThrows(InvalidDataException.class, () -> IMDBLineParser.parse(line, 10));
  }

  @Test
  public void nullStringIsReturnedOnBlankField() {
    var value = parsedLine.getNullableString(4);
    assertThat(value).isNull();
  }

  @Test
  public void nullStringIsReturnedOnIMBDNullField() {
    var value = parsedLine.getNullableString(3);
    assertThat(value).as("\\N should be considered null").isNull();
  }

  @Test
  public void errorsShouldBeThrowForGetColumnsWithoutNullValueExpected() {
    assertThrows(InvalidDataException.class, () -> parsedLine.getString(3));
    assertThrows(InvalidDataException.class, () -> parsedLine.getInteger(3));
    assertThrows(InvalidDataException.class, () -> parsedLine.getBoolean(3));
  }

  @Test
  public void errorsShouldBeThrowForInvalidIntegerValue() {
    assertThrows(InvalidDataException.class, () -> parsedLine.getInteger(0));
  }

  @Test
  public void sizeIsCalculatedCorrectly() {
    assertThat(parsedLine.size()).isEqualTo(6);
  }
}
