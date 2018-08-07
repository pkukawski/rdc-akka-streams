package pl.qki.rdc

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import pl.qki.rdc.cassandra._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object Main extends App {

  private implicit val system: ActorSystem = ActorSystem("rdc")
  private implicit val materializer: ActorMaterializer = ActorMaterializer()
  private implicit val ec: ExecutionContext = system.dispatcher

  Source
    .tick(10.seconds, 1.minute, ())
    .map(_ => System.currentTimeMillis())
    .flatMapConcat { time =>
      subscriptions(time)
        .filter(subs => (subs.lastScan + subs.interval) <= time)
    }
}
