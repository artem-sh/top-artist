package sh.app.topartist.webapp.mvc.model

import scala.reflect.BeanProperty
import javax.inject.Named
import javax.enterprise.context.RequestScoped


@Named @RequestScoped
class ArtistsForm extends Serializable {
  @BeanProperty var artists = ""
}