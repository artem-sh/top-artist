package sh.app.topartist.mvc.model

import scala.reflect.BeanProperty
import javax.inject.Named
import javax.enterprise.context.SessionScoped


@Named @SessionScoped
class ArtistsForm extends Serializable {
  @BeanProperty var artist1 = ""
  @BeanProperty var artist2 = ""
}