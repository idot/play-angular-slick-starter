package domain

import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.api.libs.json.Format
import play.api.libs.json.JsValue
import play.api.libs.json.JsString
import org.apache.commons.codec.binary.Base64
import play.api.libs.json.JsResult
import scalaz.{\/,-\/,\/-}
import play.api.libs.json.JsError
import play.api.libs.json.JsSuccess
import play.api.libs.json.Writes
import play.api.libs.json.JsObject


object JsonHelper {
  
   
   //the user object is never serialized because of the password
   //implicit val userFormat = Json.format[User]  DONTUNCOMMENT

   implicit val userNoPWFormat = Json.format[UserNoPw]        
 
   
}


