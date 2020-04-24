package cat.joanpujol.dddmovies.imdbimport.application.retriever.parser;

import cat.joanpujol.dddmovies.imdbimport.application.error.InvallidDataException;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

class IMDBLineParser {

  static @NonNull ParsedLine parse(@NonNull String line) {
    return parse(line, -1);
  }

  static ParsedLine parse(@NonNull String line, int expectedColumns) {
    String[] columns = line.split("\t");
    if (expectedColumns != -1 && columns.length != expectedColumns)
      throw new InvallidDataException(
          "Line has " + columns.length + " and " + expectedColumns + " were expected: " + line);
    return new ParsedLine(columns);
  }

  /**
   * Return line column value as given type. Null versions return null for blank strings and '\N'
   **/
  public static class ParsedLine {
    private final @NonNull String[] line;

    public ParsedLine(@NonNull String[] line) {
      this.line = line;
    }

    public @NonNull String getString(int column) {
      if (column >= line.length)
        throw new InvallidDataException(
            "Line doesn't have " + column + " columns. Line is " + Arrays.toString(line));
      return line[column];
    }

    public @Nullable String getNullableString(int column) {
      var value = getString(column);
      if (!value.isBlank() && !value.equals("\\N")) return value;
      else return null;
    }

    public @NonNull Integer getInteger(int column) {
      var value = getNullableInteger(column);
      if (value != null) return value;
      else
        throw new InvallidDataException("Error parsing column " + column + " as non blank number");
    }

    public @NonNull Boolean getBoolean(int column) {
      var value = getNullableString(column);
      if (value != null) {
        return value.equals("1");
      } else {
        throw new InvallidDataException("Error parsing column " + column + " as non blank boolean");
      }
    }

    public @Nullable Integer getNullableInteger(int column) {
      var value = getNullableString(column);
      if (value != null) {
        try {
          return Integer.parseInt(value);
        } catch (NumberFormatException e) {
          throw new InvallidDataException(
              "Error parsing column " + column + " as number. Value is " + value, e);
        }
      } else {
        return null;
      }
    }

    public @NonNull Set<String> getStringCommaSeparatedSet(int column) {
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
