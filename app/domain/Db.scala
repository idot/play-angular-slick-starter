package domain

import play.api.db.slick.Config.driver.simple._
import org.joda.time.DateTime
import play.api.db.DB

import scalaz.{\/,-\/,\/-,Validation,ValidationNel,Success,Failure}
import scalaz.syntax.apply._ 

import play.api.Logger



object Db {
   import Tables._
  
   def withT[A,B](f: => String \/ B)(implicit s: Session): String \/ B = {
       s.withTransaction{
		    try{
			   f
            } catch {
            case e: Exception => {
			  Logger.error(e.getMessage)	
              s.rollback()
              -\/(e.getMessage)
            }
          }
	   }
   } 
  
   
   def allUsers()(implicit s: Session): Seq[User] = {
       users.list
   }
      
   def authenticate(username: String, inputPassword: String)(implicit s: Session):  String \/ User = {
       val error = -\/("wrong password or user not found")
	   users.filter(u => u.username === username).firstOption.map{ u => 
         if(new org.jasypt.util.password.StrongPasswordEncryptor().checkPassword(inputPassword, u.passwordHash)){
            \/-(u)
         } else error
       }.getOrElse(error)
   }  

   def userById(id: Long)(implicit s: Session): String \/ User = {
       users.filter(u => u.id === id).firstOption.map{u => \/-(u) }.getOrElse{ -\/(s"user with id $id not found") }	   
   }		
		
   def userByUsername(username: String)(implicit s: Session): String \/ User = {
       users.filter(u => u.username === username).firstOption.map{u => \/-(u) }.getOrElse{ -\/(s"user with username $username not found") }	   
   }			
		
  /**
   *  
   */
   def insertUser(user: User)(implicit s: Session): String \/ User = {
       withT{ 
           val userId = (users returning users.map(_.id)) += user
           val userWithId = user.copy(id=Some(userId))
		   \/-(userWithId)
	   }       
   }
   
   def updateUserPassword(userId: Long, passwordHash: String)(implicit s: Session): String \/ User = {
	   withT{
          users.filter(u => u.id === userId).firstOption.map{ user => 
		      val updatedUser = user.copy(passwordHash=passwordHash)   
			  users.filter(u => u.id === userId).update(updatedUser)
			  \/-(updatedUser)		  
		  }.getOrElse(-\/(s"user not found $userId"))
       }
   }
   
   def updateUserDetails(userId: Long, firstName: String, lastName: String, email: String)(implicit s: Session): String \/ User = {
	   withT{
          users.filter(u => u.id === userId).firstOption.map{ user => 
		      val updatedUser = user.copy(firstName=firstName, lastName=lastName, email=email)   
			  users.filter(u => u.id === userId).update(updatedUser)
			  \/-(updatedUser)		  
		  }.getOrElse(-\/(s"user not found $userId"))
       }
   }
        
}


