package sh.app.topartist.mvc.controller

import java.io.Serializable
import sh.app.topartist.Config
import sh.app.topartist.mvc.model._
import javax.inject.{Inject, Named}
import javax.enterprise.context.SessionScoped
import sh.app.topartist.rating.TotalRating
import javax.faces.event.AjaxBehaviorEvent
import reflect.BeanProperty


@Named @SessionScoped
class ArtistsChartsController extends Serializable {
  @Inject private var artistsForm: ArtistsForm = _
  @Inject private var charts: Charts = _
  @BeanProperty var count: Int = 0

  def process_old(): String = {
    //    artistsRating.rating1 = new TotalRatingWrapper(artistsForm.getArtist1, Config.totalRatingRetriever.retrieveRating(artistsForm.getArtist1))
    //    artistsRating.rating2 = new TotalRatingWrapper(artistsForm.getArtist2, Config.totalRatingRetriever.retrieveRating(artistsForm.getArtist2))
    //    artistsRating.setRating1() =
    //    Config.kazantip.rateArtists(artistsForm.getContent())
    //		artistsForm.setContent("Processed lineup")


    "processedLineUp"
  }

  def countListener(event: AjaxBehaviorEvent) {
    count = count + 1
    import collection.JavaConversions._
    charts.charts = List(new Chart("vk.com", "tracks", "a1", 100, "a2", 150), new Chart("last.fm", "listeners", "b1", 200, "b2", 250))
  }

  def compareArtists(event: AjaxBehaviorEvent) {
    val artist: String = artistsForm.getArtist1
    artistsForm.setArtist1(artistsForm.getArtist2)
    artistsForm.setArtist2(artist)
  }
}