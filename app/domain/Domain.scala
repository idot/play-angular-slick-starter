package domain

import org.joda.time.DateTime


object DomainHelper {
  import org.jasypt.util.password.StrongPasswordEncryptor
  import scravatar._
    
  def encrypt(password: String): String = {
      new StrongPasswordEncryptor().encryptPassword(password)
  }
  
  def user(username: String, firstName: String, lastName: String, email: String, password: String, isAdmin: Boolean): User = {
      User(None, username, firstName, lastName, email, encrypt(password), isAdmin) 
  }
   
}

case class User(id: Option[Long] = None, username: String, firstName: String, lastName: String, email: String, passwordHash: String, isAdmin: Boolean){
   
}


case class UserNoPw(id: Option[Long] = None, username: String, firstName: String, lastName: String, isAdmin: Boolean){
}

   
object UserNoPwC {
   def apply(user: User): UserNoPw = {
        UserNoPw(user.id, user.username, user.firstName, user.lastName, user.isAdmin)  
   }     
}      
         
