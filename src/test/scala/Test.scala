import org.scalatest.{FlatSpec, Matchers}
import spray.http.StatusCodes
import spray.routing.{AuthorizationFailedRejection, HttpService}

class Test extends FlatSpec with spray.testkit.ScalatestRouteTest with Matchers with HttpService {

  override def actorRefFactory = system

  class Context {
    var timesIncrementedViaSideEffects = 0

    def route(authorized: Boolean) = authorize(authorized) {
      timesIncrementedViaSideEffects += 1
      complete("whoa")
    }
  }

  "route" should "succeed and have side effects" in new Context {
    Get() ~> route(authorized = true) ~> check {
      status shouldEqual StatusCodes.OK
      timesIncrementedViaSideEffects shouldEqual 1
    }
  }

  it should "fail and not have side effects" in new Context {
    Get() ~> route(authorized = false) ~> check {
      rejection shouldEqual AuthorizationFailedRejection
      timesIncrementedViaSideEffects shouldEqual 0
    }
  }
}
