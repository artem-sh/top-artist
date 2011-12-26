package sh.topartist.rating


trait RatingParser {
  def parseRating(httpResponse: String): Rating
}