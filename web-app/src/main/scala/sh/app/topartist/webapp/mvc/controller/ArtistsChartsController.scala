package sh.app.topartist.webapp.mvc.controller

import java.io.Serializable
import sh.app.topartist.util.CollectionsUtil.addValueToListInMap
import sh.app.topartist.mvc.model._
import sh.app.topartist.Context
import javax.inject.{Inject, Named}
import javax.enterprise.context.SessionScoped
import sh.app.topartist.rating.TotalRating
import javax.faces.event.AjaxBehaviorEvent
import reflect.BeanProperty


@Named @SessionScoped
class ArtistsChartsController extends Serializable {
  @Inject private var artistsForm: ArtistsForm = _
  @Inject private var charts: Charts = _
  @BeanProperty var vkAuthCode = ""

  def compareArtists(event: AjaxBehaviorEvent) {
    println("!!!!!!!! code = " + vkAuthCode)
    return
    val ratings = artistsForm.artists.split("\n").map(a => (a, Context.totalRatingRetriever.retrieveRating(a))).toMap
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