package com.serdeliverance.streamschops.snippets

import akka.actor.ActorSystem
import akka.stream.alpakka.slick.scaladsl.{Slick, SlickSession}
import akka.stream.scaladsl.Source
import com.serdeliverance.streamschops.domain.Domain.User
import com.serdeliverance.streamschops.infrastructure.repositories.UserTable.userTable
import com.typesafe.config.ConfigFactory
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Inserts data into slick using Slick.sink provided by Alpakka
 */
object SlickDataGenerator extends App {

  implicit val system = ActorSystem("SlickDataGenerator")

  implicit val session = SlickSession.forConfig("slick-postgres")

  val config = ConfigFactory.load()
  val numberOfRecords = config.getInt("generator.number-of-records")

  val result = Source(1 to numberOfRecords)
    .map(i => User(Some(i), s"pepe-${i}", s"1234soypepe${i}", s"pepe${i}@pepe${i}.com"))
    .runWith(Slick.sink(user => userTable += user))

  Await.ready(result, Duration.Inf)
  system.log.info("Data generated")
}
