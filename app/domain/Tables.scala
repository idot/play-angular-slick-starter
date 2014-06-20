package domain

import play.api.db.slick.Config.driver.simple._
import org.joda.time.DateTime
import scala.slick.jdbc.meta.MTable

import JodaHelper._
import org.joda.time.Period
import play.api.Logger

object JodaHelper { //TODO: check timezone, might have to use calendar 
  implicit val dateTimeColumnType = MappedColumnType.base[org.joda.time.DateTime, java.sql.Timestamp](
    { dt => new java.sql.Timestamp(dt.getMillis) },
    { ts => new org.joda.time.DateTime(ts) })
    
  implicit object DateTimeOrdering extends Ordering[DateTime] { def compare(o1: DateTime, o2: DateTime) = o1.compareTo(o2)}
  
  def compareTimeHuman(firstTime: DateTime, lastTime: DateTime): String = {
      val period = new Period(firstTime, lastTime)
      val days = period.getDays()
      val hours = period.getHours()
      val minutes = period.getMinutes()
      val seconds = period.getSeconds()
      val result = s"$days days, $hours hours, $minutes minutes, $seconds seconds"
      result  
  } 
  
}

object Tables {
  val users = TableQuery[Users]
 
 
  def createTables()(implicit s: Session) {
    users.ddl.create	
  }
  
  def drop()(implicit s: Session){
    val ddl = users.ddl 
    ddl.drop
  }

  def dropCreate()(implicit s: Session){
      if(MTable.getTables("users").list().isEmpty) {
		   Logger.debug("creating tables")
           createTables()
       }else{
		   Logger.debug("dropping and creating tables")
           drop()
           createTables()
       }
  }

  class Users(tag: Tag) extends Table[User](tag, "users") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def username = column[String]("username", O.NotNull)
    def firstname = column[String]("firstname", O.NotNull)
    def lastname = column[String]("lastname", O.NotNull)
    def email = column[String]("email", O.NotNull)
    def passwordhash = column[String]("password", O.NotNull)
    def isAdmin = column[Boolean]("isadmin", O.NotNull)
 	
	def usernameidx = index("USER_USERNAME_INDEX", (username))
	        
    def * = (id.?, username, firstname, lastname, email, passwordhash, isAdmin) <> (User.tupled, User.unapply)

  }

  

  
}


