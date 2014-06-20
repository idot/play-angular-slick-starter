package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.api.libs.json.JsObject
import play.api.data._
import play.api.data.Forms._
import play.api.db.slick.DBAction

import scalaz.{\/,-\/,\/-}

import domain._
import domain.JsonHelper._
import FormToV._


trait Users extends Controller with Security {

  def all() = DBAction { implicit rs =>
      implicit val session = rs.dbSession
      val users = Db.allUsers()
      val json =  Json.toJson(users.map(UserNoPwC(_)))
      Ok(json)
  }
  
  def userWithEmail() = withUser(){ userId => user => implicit request =>
      val nop = UserNoPwC(user)
      val jnop = Json.toJson(nop)
      val jnope = jnop.as[JsObject].deepMerge(Json.obj( "email" -> user.email ))
      Ok(jnope)
  }

  def updatePassword(username: String) = withUser(parse.json){ userId => user => implicit request =>
      implicit val session = request.dbSession
      (request.body \ "password").validate[String].fold(
             err => -\/("password not found"),
                 succ => {
                     val encryptedPassword = DomainHelper.encrypt(succ)
                     Db.updateUserPassword(userId, encryptedPassword)
                 }
      ).fold(
         err => Forbidden(Json.obj("error" -> err)),
         succ => Ok("updated user password")
      )
  }


}

object Users extends Users