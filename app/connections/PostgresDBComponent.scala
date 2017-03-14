package connections

import slick.jdbc.JdbcProfile

/**
  * Created by knoldus on 14/3/17.
  */

@Singleton
trait PostgresDBComponent extends DBComponent{

  val driver: JdbcProfile = slick.jdbc.PostgresProfile
  import driver.api._
  val db:Database = Database.forConfig("db")
}
