package sh.app.topartist.webapp.mvc.model

import javax.enterprise.context.SessionScoped
import javax.inject.Named
import reflect.BeanProperty

class RatingProvider(_name: String, _unitName: String) {
  @BeanProperty val name = _name
  @BeanProperty val unitName = _unitName
}

object RatingProvider {
  val LastFm = new RatingProvider("last.fm", "listeners")
  val PromodjRu = new RatingProvider("promodj.ru", "rank")
  val VkontakteRu = new RatingProvider("vk.com", "tracks")
}

class ArtistRating(_artist: String, _rating: Int) {
  @BeanProperty val artist = _artist
  @BeanProperty val rating = _rating
}

@Named @SessionScoped
class Charts extends Serializable {
  import collection.JavaConversions._
  @BeanProperty val charts: java.util.Map[RatingProvider, java.util.List[ArtistRating]] = collection.mutable.Map.empty[RatingProvider, java.util.List[ArtistRating]]
}