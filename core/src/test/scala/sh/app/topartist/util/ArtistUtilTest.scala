package sh.app.topartist.util

import org.scalatest.FunSuite
import sh.app.topartist.util.ArtistUtil._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class ArtistUtilTest extends FunSuite {
  test("splitArtists(), one artist only") {
    expect(List("Ivanov")) {
      splitArtists("Ivanov")
    }
  }

  test("splitArtists(), '+' divider") {
    expect(List("Pendulum DJ set", "MC WREK")) {
      splitArtists("Pendulum DJ set + MC WREK")
    }
    expect(List("Pendulum DJ set", "MC WREK")) {
      splitArtists("Pendulum DJ set           +   MC WREK")
    }
  }

  test("splitArtists(), '/' divider") {
    expect(List("Pendulum DJ set", "MC WREK")) {
      splitArtists("Pendulum DJ set / MC WREK")
    }
    expect(List("Pendulum DJ set", "MC WREK")) {
      splitArtists("Pendulum DJ set           /   MC WREK")
    }
  }
}