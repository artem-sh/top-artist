package sh.app.topartist.core.rating


class RatingParserException(e: Exception) extends Exception(e)


trait RatingParser {
  @throws(classOf[RatingParserException])
  def parseRating(httpResponse: String): Rating
}