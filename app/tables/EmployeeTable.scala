package tables

import com.google.inject.Inject
import connections.DBComponent
import models.Employee

/**
  * Created by knoldus on 14/3/17.
  */
class EmployeeTable @Inject()(dBComponent: DBComponent) {

  import dBComponent.driver.api._

  //"experienced_employee" is the table name
  //Employee is case class <-> Can be replaced here with tuple
  private[models] class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee"){

    val id: Rep[Int] = column[Int]("id")
    val name: Rep[String] = column[String]("name")
    val userName: Rep[String] = column[String]("userName")
    val email: Rep[String] = column[String]("email", O.PrimaryKey)
    val age: Rep[Int] = column[Int]("age")
    val gender: Rep[String] = column[String]("gender")
    val password: Rep[String] = column[String]("password")

    def * = (id, name, email, userName, age, gender, password) <>(Employee.tupled, Employee.unapply)
  }

  val employeeTableQuery:TableQuery[EmployeeTable] = TableQuery[EmployeeTable] //employeeTableQuery is used to create and execute queries on EmployeeTable

}

object EmployeeTable

