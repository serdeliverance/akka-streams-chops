package com.serdeliverance.streamschops.snippets

import java.nio.file.Paths

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.alpakka.file.ArchiveMetadata
import akka.stream.alpakka.file.scaladsl.Archive
import akka.stream.alpakka.slick.scaladsl.SlickSession
import akka.stream.alpakka.slick.scaladsl.Slick
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString
import com.serdeliverance.streamschops.domain.Domain.User
import com.serdeliverance.streamschops.infrastructure.repositories.UserTable.userTable
import com.serdeliverance.streamschops.snippets.Companion.CSVWrapper
import slick.jdbc.PostgresProfile.api._

/**
 * Stream that reads data from db, convert to csv record and push every element in a compressed file
 * and without compression.
 */
object RetrieveDataAsCompressedCsv extends App {
  implicit val system = ActorSystem("RetrieveDataCsvCompressed")

  implicit val session = SlickSession.forConfig("slick-postgres")

  val fileStream: Source[ByteString, NotUsed] = Slick.source(userTable.result)
    .map(user => user.toCSV)
    .map(csvRow => ByteString(csvRow + "\n"))

  val compressedFileStream =
    Source(List((ArchiveMetadata("user.csv"), fileStream)))
    .via(Archive.zip())

  // generates compressed file
  compressedFileStream.runWith(FileIO.toPath(Paths.get("tmp/result.zip")))

  // generates file without compression
  fileStream.runWith(FileIO.toPath(Paths.get("tmp/user.csv")))
}

object Companion {
  implicit class CSVWrapper(val user: User) extends AnyVal {
    def toCSV(): String = user.productIterator.map{
      case Some(value) => value
      case None => ""
      case rest => rest
    }.mkString(",")
  }
}
