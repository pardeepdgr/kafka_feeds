name := "kafka_feeds"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "2.4.0",
  "junit" % "junit" % "4.4",
  "org.scalatest" %% "scalatest" % "3.0.8",
  "org.mockito" % "mockito-core" % "2.23.4"
)