package cat.joanpujol.dddmovies.imdbimport.domain;

import com.google.common.base.Preconditions;

public class Rating extends BaseValueObject {
  public static final Rating NO_RATING = new Rating(0, 0);

  private final double averageRating;
  private final int numberOfVotes;

  public Rating(double averageRating, int numberOfVotes) {
    Preconditions.checkArgument(
        averageRating >= 0 && averageRating <= 10, "averageRating must be between [0,10]");
    Preconditions.checkArgument(numberOfVotes >= 0, "numberOfVotes must be positive");
    this.averageRating = averageRating;
    this.numberOfVotes = numberOfVotes;
  }

  public double getAverageRating() {
    return averageRating;
  }

  public int getNumberOfVotes() {
    return numberOfVotes;
  }

  @Override
  public Object[] getEqualityValues() {
    return new Object[] {averageRating, numberOfVotes};
  }
}
