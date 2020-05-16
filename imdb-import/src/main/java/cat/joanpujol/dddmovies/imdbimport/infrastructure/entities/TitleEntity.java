package cat.joanpujol.dddmovies.imdbimport.infrastructure.entities;

import java.util.ArrayList;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.checkerframework.checker.nullness.qual.Nullable;

public class TitleEntity {
  @BsonId private String id;
  private String mainTitle;
  private String originalTitle;
  private boolean adult;
  private Integer startYear;
  private @Nullable Integer endYear;
  private Integer runtimeDuration;
  private List<String> genres = new ArrayList<>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public TitleEntity() {}

  public String getMainTitle() {
    return mainTitle;
  }

  public void setMainTitle(String mainTitle) {
    this.mainTitle = mainTitle;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public Integer getStartYear() {
    return startYear;
  }

  public void setStartYear(Integer startYear) {
    this.startYear = startYear;
  }

  public @Nullable Integer getEndYear() {
    return endYear;
  }

  public void setEndYear(@Nullable Integer endYear) {
    this.endYear = endYear;
  }

  public Integer getRuntimeDuration() {
    return runtimeDuration;
  }

  public void setRuntimeDuration(Integer runtimeDuration) {
    this.runtimeDuration = runtimeDuration;
  }

  public List<String> getGenres() {
    return genres;
  }

  public void setGenres(List<String> genres) {
    this.genres = genres;
  }
}
