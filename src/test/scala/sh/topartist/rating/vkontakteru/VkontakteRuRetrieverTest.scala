package sh.topartist.rating.vkontakteru

import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito.when
import org.mockito.Matchers.any
import dispatch._

class VkontakteRuRetrieverTest extends FunSuite with MockitoSugar {
  trait TestHttpExecutor extends HttpExecutor {
    type HttpPackage[T] = T
  }

  test("example") {
    val httpMock = mock[TestHttpExecutor]
    when(httpMock.apply(any(classOf[Handler[String]]))).thenReturn("Some_HTTP_response")
    println(httpMock(url("http://google.com") as_str))
  }
}