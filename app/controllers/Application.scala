package controllers

import play.api.mvc._
import play.api.libs.json.Json
import scala.util.Random
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WS

object Application extends Controller {

  def index = Action.async {
    import play.api.Play.current
    WS.url("http://google.com").get().map { r => Ok(r.status.toString) }
  }

  val colors = IndexedSeq("red", "green", "purple", "black", "yellow", "blue", "pink")

  def number = Action(Ok(Json.toJson(Json.obj(
    "number" -> Random.nextInt(colors.size)
  ))))

  def color(i: Int) = Action {
    colors.lift(i).map { c =>
      Ok(Json.toJson(Json.obj(
        "color" -> c
      )))
    }.getOrElse(NotFound)
  }

}
