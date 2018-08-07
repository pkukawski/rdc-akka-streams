package pl.qki.rdc

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.cassandra.scaladsl.CassandraSource
import akka.stream.javadsl.Source
import com.datastax.driver.core.{Cluster, Session, SimpleStatement}
import pl.qki.rdc.subscription.Subscription

package object cassandra {

  implicit lazy val session: Session = Cluster.builder
    .addContactPoint("127.0.0.1")
    .withPort(9042)
    .build
    .connect()

  private lazy val stmt = new SimpleStatement(s"SELECT * FROM rdc.subscriptions").setFetchSize(20)


  def subscriptions(since: Long)(implicit as: ActorSystem, mat: ActorMaterializer): Source[Subscription, NotUsed] = {
    Source.fromGraph(CassandraSource(stmt).map { row =>
      Subscription(
        row.getString("id"),
        row.getString("field"),
        row.getString("index"),
        row.getString("query"),
        row.getString("scanTable"),
        row.getLong("interval"),
        row.getLong("lastScan"),
        row.getString("logTable")
      )
    })
  }

  def scan(subs: Subscription)(implicit as: ActorSystem, mat: ActorMaterializer): Source[Subscription, NotUsed] = {
    ???
  }

}
