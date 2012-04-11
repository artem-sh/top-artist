package sh.app.topartist.festival.parser

import io.Source
import java.net.URL
import scala.util.parsing.combinator.RegexParsers
import sh.app.topartist.util.AssertionUtil
import sh.app.topartist.LineUp


class Kazantip19LineUpParser extends RegexParsers {
  private var _artistName: String = _
  private var _when: String = _
  private var _where: String = _

  override def skipWhitespace = false

  private def when = """\d{1,2},\d{1,2}""".r ^^ {s => _when = s}

  private def artistName = """[^(]+""".r ^^ {s => _artistName = s.trim()}

  private def where = """[^)]+""".r ^^ {s => _where = s.trim()}

  private def line = """\s*""".r ~ when ~ """\s*-\s*""".r ~ artistName ~ "(" ~ where ~ ")"

  private def parseLine(str: String): Option[(String, String, String)] = {
    if (str.matches("^\n*") || str.matches("^\r\n*")) return None
    println("Kazantip19LineUpParser.parseLine(), line to parse is: '" + str + "'")

    parse(line, str) match {
      case Success(result, next) => {
        AssertionUtil.checkParamIsNotNull(_artistName)
        AssertionUtil.checkParamIsNotNull(_when)
        AssertionUtil.checkParamIsNotNull(_where)
        Some(_artistName, _when, _where)
      }
      case NoSuccess(msg, next) => println(msg); None
    }
  }

  def parseLineUp(lineUpUrl: URL): LineUp = {
    parseLineUp(Source.fromURL(lineUpUrl))
  }

  def parseLineUp(lineUpContent: String): LineUp = {
    parseLineUp(Source.fromString(lineUpContent))
  }

  private def parseLineUp(lineUpSource: Source): LineUp = {
    val lineUp = new LineUp()
    lineUpSource.getLines().foreach(line => {
      parseLine(line) match {
        case Some(parsed) => lineUp.addPerformance(parsed._1, parsed._2, parsed._3)
        case None => println("nothing found in current line")
      }
    })

    lineUp
  }
}