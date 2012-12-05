package sh.app.topartist.mvc.model


import javax.enterprise.context.RequestScoped
import javax.inject.Named
import reflect.BeanProperty

@Named
@RequestScoped
class ArtistsRating extends Serializable {
  @BeanProperty var ratings: List[TotalRatingWrapper] = _
}