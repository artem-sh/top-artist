package sh.app.topartist.mvc.model

import scala.reflect.BeanProperty
import javax.inject.Named
import javax.enterprise.context.SessionScoped


@Named @SessionScoped
class RawLineUp extends Serializable {
  @BeanProperty var content = ""
}