package sh.app.topartist.mvc.model


import javax.enterprise.context.SessionScoped
import javax.inject.Named
import reflect.BeanProperty

@Named
@SessionScoped
class ArtistsRating extends Serializable {
  @BeanProperty var ratings: java.util.List[TotalRatingWrapper] = _
}