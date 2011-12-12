package sh.topartist.util.raisin

import org.scalatest.FunSuite
import sh.topartist.util.raisin.Raisin._


class RaisinTest extends FunSuite {

  private class BooleanHolder {
    var flag1 = false
    var flag2 = false
  }

  private class MyResource1(holder: BooleanHolder) extends Disposable {
    override def dispose() {holder.flag1 = true}
  }

  private class MyResource2(holder: BooleanHolder) extends Disposable {
    override def dispose() {holder.flag2 = true}
  }

  test("using(), one param") {
    val holder = new BooleanHolder
    using(new MyResource1(holder)) {r1 =>}

    assert(holder.flag1)
  }

  test("using(), two params") {
    val holder = new BooleanHolder
    using(new MyResource1(holder), new MyResource2(holder)) {(r1, r2) =>}

    assert(holder.flag1)
    assert(holder.flag2)
  }
}