ThisBuild / scalaVersion     := "2.13.3"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.github.serdeliverance"
ThisBuild / organizationName := "serdeliverance"

lazy val akkaVersion = "2.6.8"
lazy val postgresVersion = "42.2.2"
lazy val logbackVersion = "1.2.3"
lazy val JacksonVersion = "2.11.4"
lazy val alpakkaVersion = "2.0.2"

lazy val root = (project in file("."))
  .settings(
    name := "akka-streams-chops",
    libraryDependencies ++= Seq(
      // Actors and Streams
      "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
      "com.typesafe.akka" %% "akka-stream"              % akkaVersion,
      "com.lightbend.akka" %% "akka-stream-alpakka-slick" % alpakkaVersion,
      "com.lightbend.akka" %% "akka-stream-alpakka-file" % alpakkaVersion,
      "com.typesafe.akka" %% "akka-stream-kafka" % "2.1.1",
      // JDBC with PostgreSQL
      "org.postgresql" % "postgresql" % postgresVersion,
      // Logger
      "ch.qos.logback"    % "logback-classic"           % logbackVersion,
      // misc
      "com.fasterxml.jackson.core" % "jackson-databind" % JacksonVersion,
      // Test
      "org.scalatest" %% "scalatest" % "3.2.2"
    )
  )