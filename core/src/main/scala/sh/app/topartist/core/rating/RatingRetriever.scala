package sh.app.topartist.core.rating


trait RatingRetriever {
  def retrieveRating(artistName: String, sessionData: SessionData): Rating
}