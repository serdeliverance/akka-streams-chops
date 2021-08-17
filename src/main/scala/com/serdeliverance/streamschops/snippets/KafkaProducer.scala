package com.serdeliverance.streamschops.snippets

import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.common.serialization.StringSerializer

object KafkaProducer {

  implicit val system = ActorSystem()

  val config = ConfigFactory.load("kafka.producer")
  val producerSettings =
    ProducerSettings(config, new StringSerializer, new StringSerializer)
      .withBootstrapServers("localhost:9092") // TODO remove hardcode

}
