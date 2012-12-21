package sh.app.topartist.webapp.service

import dispatch.url
import javax.inject.{Inject, Named}
import javax.enterprise.context.ApplicationScoped
import sh.app.topartist.webapp.{WebAppSessionData, Context}
import reflect.BeanProperty
import org.slf4j.LoggerFactory
import net.liftweb.json._
import sh.app.topartist.core.util.JsonUtil


@Named @ApplicationScoped
class VkontakteRuAuthenticator extends Serializable {
  private val log = LoggerFactory.getLogger(this.getClass)
  @BeanProperty val vkAuthCodeRequestUrl = "https://oauth.vk.com/authorize?" +
    "client_id=" + Context.config.property("vk.app.id") + "&" +
    "scope=audio&" +
    "redirect_uri=" + Context.config.property("vk.redirect.uri") + "&" +
    "response_type=code"
  @Inject var webAppSessionData: WebAppSessionData = _

  def authenticate {
    require(!webAppSessionData.vkontakteRuAuthCode.isEmpty)

    if (webAppSessionData.vkontakteRuAccessToken != None) return

    val request =
      url("https://oauth.vk.com/access_token") <<? List(
        ("client_id", Context.config.property("vk.app.id")),
        ("client_secret", Context.config.property("vk.app.secret")),
        ("redirect_uri", Context.config.property("vk.redirect.uri")),
        ("code", webAppSessionData.vkontakteRuAuthCode))
    try {
      val json = parse(Context.httpClient(request as_str))
      webAppSessionData.vkontakteRuAccessToken = Some(JsonUtil.extractStringValue(json \ "access_token"))
    } catch {
      case e: Exception => log.error("Vk.com authentication failed", e)
    }
  }
}