package sh.app.topartist.rating


trait RatingRetriever {
  def retrieveRating(artistName: String): Rating
}