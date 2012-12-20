package sh.app.topartist.app

import sh.app.topartist.core.Context


object App extends scala.App {
  val lineUpStr = """31,07 - Cosmonaut (SUNSET Point )
      6,08 - Pendulum + MC WREK (MAIN STAGE) OPENING CEREMONY
      20,08 - Groove Armada / ANDY CATO (MAIN STAGE) CLOSING PARTY"""

  try {
    val lineUp = Context.kazantip.rateArtists(lineUpStr)
    lineUp.artists.sortWith(_ > _).foreach(println)
  } finally {
    Context.httpClient.shutdown()
  }
}