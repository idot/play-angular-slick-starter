import play.api._
import play.api.mvc._
import play.filters.headers.SecurityHeadersFilter
import controllers.PlayHelper


//object Global extends WithFilters(SecurityHeadersFilter()) with GlobalSettings {
object Global extends GlobalSettings {

  override def onStart(app: Application) {
	  val debug = PlayHelper.debug
	  val insertdata = Play.current.configuration.getBoolean("application.insertdata").getOrElse(false) 
	  val debugString = if(debug){ "\nXXXXXXXXX debug mode XXXXXXXXX"}else{ "production" }
	  Logger.info("starting up "+debugString)
      if(debug && insertdata){
	     InitialData.insert(debug)
      }
  }
 
  override def onStop(app: Application){
	  Logger.info("shutdown")
  }
}
