package sh.app.topartist.mvc.controller

import java.io.Serializable
import sh.app.topartist.util.CollectionsUtil.addValueToListInMap
import sh.app.topartist.mvc.model._
import javax.inject.{Inject, Named}
import javax.enterprise.context.SessionScoped
import sh.app.topartist.rating.{Rating, TotalRating}
import javax.faces.event.AjaxBehaviorEvent
import sh.app.topartist.rating.lastfm.LastFmRating
import sh.app.topartist.rating.promodjru.PromodjRuRating
import sh.app.topartist.rating.vkontakteru.VkontakteRuRating
import sh.app.topartist.Config


@Named @SessionScoped
class ArtistsChartsController extends Serializable {
  @Inject private var artistsForm: ArtistsForm = _
  @Inject private var charts: Charts = _

  def process_old(): String = {
    //    artistsRating.rating1 = new TotalRatingWrapper(artistsForm.getArtist1, Config.totalRatingRetriever.retrieveRating(artistsForm.getArtist1))
    //    artistsRating.rating2 = new TotalRatingWrapper(artistsForm.getArtist2, Config.totalRatingRetriever.retrieveRating(artistsForm.getArtist2))
    //    artistsRating.setRating1() =
    //    Config.kazantip.rateArtists(artistsForm.getContent())
    //		artistsForm.setContent("Processed lineup")


    "processedLineUp"
  }

  def compareArtists(event: AjaxBehaviorEvent) {
    val ratings = artistsForm.artists.split("\n").map(a => (a, Config.totalRatingRetriever.retrieveRating(a))).toMap
    transformRatingsToCharts(ratings, charts)
    //    charts.charts.put(RatingProvider.LastFm, asList(new ArtistRating("a1", 100)))
    //    charts.charts.put(RatingProvider.PromodjRu, asList(new ArtistRating("a1", 200)))
    //    charts.charts.put(RatingProvider.VkontakteRu, asList(new ArtistRating("a1", 300)))
  }

  private def transformRatingsToCharts(ratings: Map[String, TotalRating], charts: Charts) {
    ratings.foreach(a2r => {
      addValueToListInMap(charts.charts, RatingProvider.LastFm, new ArtistRating(a2r._1, a2r._2.lastFmRating.listeners))
      addValueToListInMap(charts.charts, RatingProvider.PromodjRu, new ArtistRating(a2r._1, a2r._2.promodjRuRating.promoRank))
      addValueToListInMap(charts.charts, RatingProvider.VkontakteRu, new ArtistRating(a2r._1, a2r._2.vkontakteRuRating.tracksCount))
    })
  }
}