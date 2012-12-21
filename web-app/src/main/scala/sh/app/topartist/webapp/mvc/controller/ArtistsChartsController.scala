package sh.app.topartist.webapp.mvc.controller

import sh.app.topartist.core.util.CollectionsUtil.addValueToListInMap
import sh.app.topartist.webapp.mvc.model._
import javax.inject.{Inject, Named}
import sh.app.topartist.core.rating.{SessionData, TotalRating}
import javax.faces.event.AjaxBehaviorEvent
import sh.app.topartist.webapp.{WebAppSessionData, Context}
import javax.faces.bean.ViewScoped


@Named @ViewScoped
class ArtistsChartsController extends Serializable {
  @Inject private var artistsForm: ArtistsForm = _
  @Inject private var charts: Charts = _
  @Inject private var webAppSessionData: WebAppSessionData = _

  def compareArtists(event: AjaxBehaviorEvent) {
    val ratings = artistsForm.artists.split("\n").map(a =>
      (a, Context.totalRatingRetriever.retrieveRating(a, SessionData(webAppSessionData.vkontakteRuAccessToken)))
    ).toMap
    transformRatingsToCharts(ratings, charts)
  }

  private def transformRatingsToCharts(ratings: Map[String, TotalRating], charts: Charts) {
    ratings.foreach(a2r => {
      addValueToListInMap(charts.charts, RatingProvider.LastFm, new ArtistRating(a2r._1, a2r._2.lastFmRating.listeners))
      addValueToListInMap(charts.charts, RatingProvider.PromodjRu, new ArtistRating(a2r._1, a2r._2.promodjRuRating.promoRank))
      addValueToListInMap(charts.charts, RatingProvider.VkontakteRu, new ArtistRating(a2r._1, a2r._2.vkontakteRuRating.tracksCount))
    })
  }
}