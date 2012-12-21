package sh.app.topartist.webapp

import javax.inject.Named
import reflect.BeanProperty
import javax.enterprise.context.SessionScoped

@Named @SessionScoped
class WebAppSessionData extends Serializable {
  var vkontakteRuAccessToken: Option[String] = None
  @BeanProperty var vkontakteRuAuthCode: String = _
}