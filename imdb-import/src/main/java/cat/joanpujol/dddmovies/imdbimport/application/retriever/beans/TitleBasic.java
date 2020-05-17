package cat.joanpujol.dddmovies.imdbimport.application.retriever.beans;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.immutables.value.Value;

/**
 * IMDB title.basics register. Example:
 *
 * <pre>
 *     tconst	titleType	primaryTitle	originalTitle	isAdult	startYear	endYear	runtimeMinutes	genres
 *     tt0000001	short	Carmencita	Carmencita	0	1894	\N	1	Documentary,Short
 * </pre>
 */
@Value.Immutable
public interface TitleBasic {
  String id();

  String type();

  String primaryTitle();

  String originalTitle();

  boolean adult();

  Integer startYear();

  @Nullable
  Integer endYear();

  @Nullable
  Integer runtimeMinutes();

  Set<String> genres();
}
