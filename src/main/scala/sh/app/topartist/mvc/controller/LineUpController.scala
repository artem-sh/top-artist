package sh.app.topartist.mvc.controller

import java.io.Serializable
import sh.app.topartist.Config
import sh.app.topartist.mvc.model.RawLineUp
import javax.inject.{Inject, Named}
import javax.enterprise.context.RequestScoped


@Named @RequestScoped
class LineUpController extends Serializable {
	@Inject private var rawLineUp: RawLineUp = _

  def process(): String = {
    Config.kazantip.rateArtists(rawLineUp.getContent())
		rawLineUp.setContent("Processed lineup")
    "processedLineUp"
  }
}