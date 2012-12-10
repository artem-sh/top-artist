package sh.app.topartist.mvc.model

import javax.enterprise.context.SessionScoped
import javax.inject.Named
import reflect.BeanProperty

@Named @SessionScoped
class Charts extends Serializable {
  @BeanProperty var charts: java.util.List[Chart] = _
}