package com.serdeliverance.streamschops.infrastructure.repositories

import com.serdeliverance.streamschops.domain.User
import slick.jdbc.PostgresProfile.api._

class UserTable(tag: Tag) extends Table[User](tag, "users") {
  def id: Rep[Option[Int]] = column[Option[Int]]("id")

  def username: Rep[String] = column[String]("username")

  def password: Rep[String] = column[String]("password")

  def email: Rep[String] = column[String]("email")

  def * = (id, username, password, email).mapTo[User]
}

object UserTable {
  val userTable = TableQuery[UserTable]
}
