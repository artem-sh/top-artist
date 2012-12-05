package sh.app.topartist.mvc.controller

import java.io.Serializable
import sh.app.topartist.Config
import sh.app.topartist.mvc.model.{ArtistsRating, TotalRatingWrapper, ArtistsForm}
import javax.inject.{Inject, Named}
import javax.enterprise.context.RequestScoped
import sh.app.topartist.rating.TotalRating


@Named
@RequestScoped
class LineUpController extends Serializable {
  @Inject private var artistsForm: ArtistsForm = _
  @Inject private var artistsRating: ArtistsRating = _

  def process_old(): String = {
//    artistsRating.rating1 = new TotalRatingWrapper(artistsForm.getArtist1, Config.totalRatingRetriever.retrieveRating(artistsForm.getArtist1))
//    artistsRating.rating2 = new TotalRatingWrapper(artistsForm.getArtist2, Config.totalRatingRetriever.retrieveRating(artistsForm.getArtist2))
//    artistsRating.setRating1() =
    //    Config.kazantip.rateArtists(artistsForm.getContent())
    //		artistsForm.setContent("Processed lineup")


    "processedLineUp"
  }

  def process() {
    //    "processedLineUp"
  }
}