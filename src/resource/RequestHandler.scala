package resource

import argonaut.{Json, Parse}
import entities.Item
import user.User

import scala.io.Source
import scala.util.Using

/** * Singleton object that is responsible for handling the loading
 * of the .json files that application specific information is stored in
 */

object RequestHandler {


    def validate_user(username: String, password: String): Option[User] = {
        val table = load_database("USERS")
        if (table.isEmpty) return None
        else {
            val received = table.get
            val decoded = Parse.decodeOption[List[User]](received.toString())
            if (decoded.isEmpty) return None
            val valid = (u: User) => u.username == username && u.password == password
            val exists = decoded.get.exists(valid)
            if (exists) return Some(decoded.get.filter(valid).head)
        }
        None
    }


    def load_menu: Option[List[Item]] = {
        val table = load_database("MENU_ITEMS")
        if (table.isEmpty) None
        else {
            val received = table.get
            val decoded = Parse.decodeOption[List[Item]](received.toString())
            if (decoded.isEmpty) None
            else decoded
        }
    }

    def load_database(db_name: String): Option[Json] = {
        val resource = getClass.getResource(s"/database_tables/$db_name.json").toURI
        val source = Using(Source.fromFile(resource)) {
            _.mkString
        }
        if (source.isSuccess) Parse.parseOption(source.get)
        else None
    }

}
