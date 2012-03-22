package sh.app.topartist.mvc.controller

import java.io.Serializable
import javax.enterprise.context.SessionScoped
import sh.app.topartist.Config
import sh.app.topartist.mvc.model.RawLineUp
import javax.inject.{Inject, Named}


@Named @SessionScoped
class LineUpController extends Serializable {
	@Inject private var rawLineUp: RawLineUp = null

  def process(): String = {
    Config.kazantip.rateArtists(rawLineUp.getContent())
    "processedLineUp"
  }
}