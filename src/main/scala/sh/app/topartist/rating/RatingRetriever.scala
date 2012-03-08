package sh.topartist.rating


trait RatingRetriever {
  def retrieveRating(artistName: String): Rating
}