package com.serdeliverance.streamschops.snippets

import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.Source
import com.serdeliverance.streamschops.domain.Measurement
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

import java.time.LocalDateTime
import scala.concurrent.duration.DurationInt
import scala.util.Random

object KafkaProducer {

  implicit val system = ActorSystem()

  val config = ConfigFactory.load("akka.kafka.producer")

  val producerSettings =
    ProducerSettings(config, new StringSerializer, new StringSerializer)
      .withBootstrapServers("localhost:9092") // TODO remove hardcode

  val random = new Random()

  // TODO demo-topic to variable
  // TODO add serializer for value (maybe something with circe)
  Source(1 to Int.MaxValue)
    .map(id => Measurement(id, random.nextInt(), LocalDateTime.now))
    .map(value => new ProducerRecord[String, String]("demo-topic", value.toString))
    .throttle(10, 1.second)
    .runWith(Producer.plainSink(producerSettings))

}
