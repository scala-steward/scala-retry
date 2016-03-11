import org.scalatest.{FlatSpec, Matchers}
import util.retry.blocking.{Success, Retry, RetryStrategy}

import scala.concurrent.duration._

/**
  * Created by dev on 11/03/16.
  */
class RetryDslSpec extends FlatSpec with Matchers {

  implicit val retryStrategy =
    RetryStrategy.fixedWait(retryDuration = 1.seconds, maxRetries = 2)

  "A `Retry` " should "be used in for comprehensions" in {
    val result = for {
      x <- Retry(1 / 1) // fails
      y <- Retry(1 / 1) // success
    } yield x + y

    result should be(Success(2))
  }

}
