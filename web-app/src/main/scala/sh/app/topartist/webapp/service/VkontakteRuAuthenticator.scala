package sh.app.topartist.webapp.service

import dispatch.url
import javax.inject.Named
import javax.enterprise.context.ApplicationScoped
import sh.app.topartist.webapp.Context
import reflect.BeanProperty
import org.slf4j.LoggerFactory
import net.liftweb.json._
import sh.app.topartist.core.util.JsonUtil
import java.io.Serializable


@Named
@ApplicationScoped
class VkontakteRuAuthenticator extends Serializable {
  private val log = LoggerFactory.getLogger(this.getClass)
  @BeanProperty val vkAuthCodeRequestUrl = "https://oauth.vk.com/authorize?" +
    "client_id=" + Context.config.property("vk.app.id") + "&" +
    "scope=audio&" +
    "redirect_uri=" + Context.config.property("vk.redirect.uri") + "&" +
    "response_type=code"
  @BeanProperty var vkAuthCode = ""
  private var _accessToken: Option[String] = None

  def authenticate = {
    require(!vkAuthCode.isEmpty)

    val request =
      url("https://oauth.vk.com/access_token") <<? List(
        ("client_id", Context.config.property("vk.app.id")),
        ("client_secret", Context.config.property("vk.app.secret")),
        ("redirect_uri", Context.config.property("vk.redirect.uri")),
        ("code", vkAuthCode))
    try {
      val json = parse(Context.httpClient(request as_str))
      _accessToken = Some(JsonUtil.extractStringValue(json \ "access_token"))
    } catch {
      case e: Exception => log.error("Vk.com authentication failed", e)
    }
  }

  def accessToken = _accessToken.getOrElse(throw new IllegalStateException())
}