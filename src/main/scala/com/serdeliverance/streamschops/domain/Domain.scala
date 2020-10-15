package com.serdeliverance.streamschops.domain

object Domain {
  case class User(id: Option[Int], username: String, password: String, email: String)
}
