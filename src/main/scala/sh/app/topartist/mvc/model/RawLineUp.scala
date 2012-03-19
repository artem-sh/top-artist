package sh.app.topartist.mvc.model

import javax.faces.bean._
import scala.reflect.BeanProperty


@ManagedBean
@SessionScoped
class RawLineUp extends Serializable {
  @BeanProperty var content = ""
}