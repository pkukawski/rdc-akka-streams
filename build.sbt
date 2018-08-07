name := "rdc-akka-streams"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.5.14",
  "com.lightbend.akka" %% "akka-stream-alpakka-cassandra" % "0.20",
  "com.lightbend.akka" %% "akka-stream-alpakka-elasticsearch" % "0.20",
  "org.hbase" % "asynchbase" % "1.8.2",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"

)