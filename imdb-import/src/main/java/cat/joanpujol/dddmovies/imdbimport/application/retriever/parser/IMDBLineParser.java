package cat.joanpujol.dddmovies.imdbimport.application.retriever.parser;

import cat.joanpujol.dddmovies.imdbimport.application.error.InvalidDataException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

class IMDBLineParser {

  private IMDBLineParser() {}

  static ParsedLine parse(String line) {
    return parse(line, -1);
  }

  static ParsedLine parse(String line, int expectedColumns) {
    String[] columns = line.split("\t");
    if (expectedColumns != -1 && columns.length != expectedColumns)
      throw new InvalidDataException(
          "Line has " + columns.length + " and " + expectedColumns + " were expected: " + line);
    return new ParsedLine(columns);
  }

  /**
   * Return line column value as given type. Null versions return null for blank strings and '\N'
   */
  public static class ParsedLine {
    private final String[] line;

    public ParsedLine(String[] line) {
      this.line = line;
    }

    public String getString(int column) {
      if (column >= line.length)
        throw new InvalidDataException(
            "Line doesn't have " + column + " columns. Line is " + Arrays.toString(line));
      return line[column];
    }

    public @Nullable String getNullableString(int column) {
      var value = getString(column);
      if (!value.isBlank() && !value.equals("\\N")) return value;
      else return null;
    }

    public Integer getInteger(int column) {
      var value = getNullableInteger(column);
      if (value != null) return value;
      else
        throw new InvalidDataException(
            String.format("Error parsing column %d as non blank number", column));
    }

    public Boolean getBoolean(int column) {
      var value = getNullableString(column);
      if (value != null) {
        return value.equals("1");
      } else {
        throw new InvalidDataException(
            String.format("Error parsing column %d as non blank boolean", column));
      }
    }

    public @Nullable Integer getNullableInteger(int column) {
      var value = getNullableString(column);
      if (value != null) {
        try {
          return Integer.parseInt(value);
        } catch (NumberFormatException e) {
          throw new InvalidDataException(
              String.format("Error parsing column %d as number. Value is %s", column, value), e);
        }
      } else {
        return null;
      }
    }

    public Set<String> getStringCommaSeparatedSet(int column) {
      var value = getString(column);
      String[] values = value.split(",", -1);
      var set = new LinkedHashSet<String>();
      Collections.addAll(set, values);
      return set;
    }

    public int size() {
      return line.length;
    }
  }
}
