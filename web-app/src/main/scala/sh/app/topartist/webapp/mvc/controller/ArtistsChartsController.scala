package sh.app.topartist.webapp.mvc.controller

import java.io.Serializable
import sh.app.topartist.core.util.CollectionsUtil.addValueToListInMap
import sh.app.topartist.webapp.mvc.model._
import javax.inject.{Inject, Named}
import javax.enterprise.context.SessionScoped
import sh.app.topartist.core.rating.{SessionData, TotalRating}
import javax.faces.event.AjaxBehaviorEvent
import sh.app.topartist.webapp.Context
import sh.app.topartist.webapp.service.VkontakteRuAuthenticator
import reflect.BeanProperty


@Named
@SessionScoped
class ArtistsChartsController extends Serializable {
  @Inject private var artistsForm: ArtistsForm = _
  @Inject private var charts: Charts = _
  @BeanProperty @Inject var vkontakteRuAuthenticator: VkontakteRuAuthenticator = _

  def compareArtists(event: AjaxBehaviorEvent) {
    val ratings = artistsForm.artists.split("\n").map(a =>
      (a, Context.totalRatingRetriever.retrieveRating(a, SessionData(vkontakteRuAuthenticator.accessToken)))
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