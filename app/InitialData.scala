import play.api._
import play.api.Play.current
import play.api.Logger
import play.api.db.slick._

import org.apache.commons.io.IOUtils
import domain._
import play.api.db.slick.Config.driver.simple._

/**
* 
**/
object InitialData {
  import org.joda.time.format.DateTimeFormat
  import org.joda.time.DateTime

  
  def users(debug: Boolean): Seq[User] = {
	  Seq(
		  DomainHelper.user("admin","admin1","admin1","email@email.com", "pw", true),
		  DomainHelper.user("user1","first1", "last1","email@email.com", "pw", false),
		  DomainHelper.user("user1","first1", "last1","email@email.com", "pw", false)
      )
  }
  
  def insert(debug: Boolean): Unit = { //again slick is missing nested transactions!
    val us = users(debug)
    Logger.info("inserting data in db")
    DB.withSession { implicit s: Session =>
	   Tables.dropCreate()
       Logger.info("inserting data")
	   us.foreach{ u => Db.insertUser(u) }      	   
       Logger.info("inserted data")
    }
    Logger.info("done inserting data in db")
  
  }


}
