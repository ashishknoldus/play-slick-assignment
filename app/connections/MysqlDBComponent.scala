package connections

/**
  * Created by knoldus on 13/3/17.
  */
@Singleton
trait MysqlDBComponent extends DBComponent{

  val driver  = slick.jdbc.MySQLProfile

  import driver.api._

  val db: Database = Database.forConfig("db")
}
