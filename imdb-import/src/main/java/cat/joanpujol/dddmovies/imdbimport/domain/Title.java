package cat.joanpujol.dddmovies.imdbimport.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.time.Year;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class Title extends BaseEntity {
  private TitleName mainTitle;
  private TitleName originalTitle;
  private Adult adult;
  private Year startYear;
  private @Nullable Year endYear;
  private @Nullable RuntimeDuration runtimeDuration;
  private Genres genres;
  private Rating rating = Rating.NO_RATING;

  public Title(
      Id id,
      TitleName mainTitle,
      TitleName originalTitle,
      Adult adult,
      Year startYear,
      Genres genres) {
    super(id);
    this.mainTitle = mainTitle;
    this.originalTitle = originalTitle;
    this.adult = adult;
    this.startYear = startYear;
    this.genres = genres;
  }

  public TitleName getMainTitle() {
    return mainTitle;
  }

  public TitleName getOriginalTitle() {
    return originalTitle;
  }

  public Adult getAdult() {
    return adult;
  }

  public Year getStartYear() {
    return startYear;
  }

  public @Nullable Year getEndYear() {
    return endYear;
  }

  public @Nullable RuntimeDuration getRuntimeDuration() {
    return runtimeDuration;
  }

  public void setMainTitle(TitleName mainTitle) {
    this.mainTitle = mainTitle;
  }

  public void setOriginalTitle(TitleName originalTitle) {
    this.originalTitle = originalTitle;
  }

  public void setAdult(Adult adult) {
    this.adult = adult;
  }

  public void setStartYear(Year startYear) {
    Preconditions.checkArgument(
        endYear == null || startYear.compareTo(endYear) <= 0,
        "Start year must be smaller than end year");
    this.startYear = startYear;
  }

  public void setEndYear(@Nullable Year endYear) {
    Preconditions.checkArgument(
        endYear == null || endYear.compareTo(startYear) >= 0,
        "End year must be greater than start year");
    this.endYear = endYear;
  }

  public void setRuntimeDuration(@Nullable RuntimeDuration runtimeDuration) {
    this.runtimeDuration = runtimeDuration;
  }

  public void setGenres(Genres genres) {
    this.genres = genres;
  }

  public Genres getGenres() {
    return genres;
  }

  public Rating getRating() {
    return rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", getId())
        .add("mainTitle", mainTitle)
        .toString();
  }
}
