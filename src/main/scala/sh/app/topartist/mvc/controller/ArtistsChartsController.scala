package sh.app.topartist.mvc.controller

import java.io.Serializable
import sh.app.topartist.Config
import sh.app.topartist.mvc.model._
import javax.inject.{Inject, Named}
import javax.enterprise.context.SessionScoped
import sh.app.topartist.rating.{Rating, TotalRating}
import javax.faces.event.AjaxBehaviorEvent
import reflect.BeanProperty
import sh.app.topartist.rating.lastfm.LastFmRating
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import sh.app.topartist.rating.promodjru.PromodjRuRating
import sh.app.topartist.rating.vkontakteru.VkontakteRuRating


@Named @SessionScoped
class ArtistsChartsController extends Serializable {
  @Inject private var artistsForm: ArtistsForm = _
  @Inject private var chartss: Charts = _

  def process_old(): String = {
    //    artistsRating.rating1 = new TotalRatingWrapper(artistsForm.getArtist1, Config.totalRatingRetriever.retrieveRating(artistsForm.getArtist1))
    //    artistsRating.rating2 = new TotalRatingWrapper(artistsForm.getArtist2, Config.totalRatingRetriever.retrieveRating(artistsForm.getArtist2))
    //    artistsRating.setRating1() =
    //    Config.kazantip.rateArtists(artistsForm.getContent())
    //		artistsForm.setContent("Processed lineup")


    "processedLineUp"
  }

  def compareArtists(event: AjaxBehaviorEvent) {
    chartss.charts.put(RatingProvider.LastFm, java.util.Arrays.asList(new ArtistRating("a1", 100)))
    chartss.charts.put(RatingProvider.PromodjRu, java.util.Arrays.asList(new ArtistRating("a1", 200)))
    chartss.charts.put(RatingProvider.VkontakteRu, java.util.Arrays.asList(new ArtistRating("a1", 300)))

    val artist: String = artistsForm.getArtist1
    artistsForm.setArtist1(artistsForm.getArtist2)
    artistsForm.setArtist2(artist)
  }

  private def extractChartFromRating(artist1: String, rating1: Rating, artist2: String, rating2: Rating) {
    val ratingProvider: RatingProvider = rating1 match {
      case _: LastFmRating => new RatingProvider("last.fm", "listeners")
      case _: PromodjRuRating => new RatingProvider("promodj.ru", "rank")
      case _: VkontakteRuRating => new RatingProvider("vk.com", "tracks")
    }
  }

//  private def transformRatingsToCharts(ratings: Map[String, TotalRating]) {
//    ratings.foreach(a2r => {
//      chartss.charts(RatingProvider.LastFm) :+ new ArtistRating(a2r._1, a2r._2.lastFmRating.listeners)
//      chartss.charts(RatingProvider.PromodjRu) :+ new ArtistRating(a2r._1, a2r._2.promodjRuRating.promoRank)
//      chartss.charts(RatingProvider.VkontakteRu) :+ new ArtistRating(a2r._1, a2r._2.vkontakteRuRating.tracksCount)
//    })
//  }
}