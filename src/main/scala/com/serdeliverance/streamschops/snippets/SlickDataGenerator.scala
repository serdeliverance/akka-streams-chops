package com.serdeliverance.streamschops.snippets

import akka.actor.ActorSystem
import akka.stream.alpakka.slick.scaladsl.SlickSession

/**
 * Inserts data into slick using Slick.sink provided by Alpakka
 */
object SlickDataGenerator extends App {

  implicit val system = ActorSystem("SlickDataGenerator")

  implicit val session = SlickSession.forConfig("slick-mysql")
}
