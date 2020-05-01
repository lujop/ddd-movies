package cat.joanpujol.dddmovies.imdbimport.application.updater.titlebasics;

import cat.joanpujol.dddmovies.imdbimport.application.common.Request;
import cat.joanpujol.dddmovies.imdbimport.application.retriever.beans.TitleBasic;
import org.immutables.value.Value;

@Value.Immutable
public interface UpdateTitleBasicsRequest extends Request {
  @Value.Parameter
  TitleBasic titleBasic();
}
