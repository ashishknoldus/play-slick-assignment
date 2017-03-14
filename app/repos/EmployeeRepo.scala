package repos

import com.google.inject.Inject
import connections.DBComponent
import models.Employee
import tables.EmployeeTable

import scala.concurrent.Future

/**
  * Created by knoldus on 14/3/17.
  */
class EmployeeRepo @Inject()(dBComponent: DBComponent) extends EmployeeTable(dBComponent: DBComponent) {

  import dBComponent.driver.api._

  //dBComponent.db is the object that is specified with the details of DB

  def dropTable = dBComponent.db.run {employeeTableQuery.schema.drop}

  def create = dBComponent.db.run{ employeeTableQuery.schema.create } //dBComponent.db.run return the O/P wrapped in Future

  def insert(empList: List[Employee]) = {

    val dbioList = empList.map(emp => {
      employeeTableQuery += emp
    })

    val action = DBIO.sequence(dbioList)

    dBComponent.db.run{ action }

  }

  def insertOrUpdate(emp: Employee) = dBComponent.db.run{ employeeTableQuery.insertOrUpdate(emp) }

  def delete(exp: Int) = dBComponent.db.run{ employeeTableQuery.filter(employee => employee.age <= 18).delete }

  def getAll()  = dBComponent.db.run{ employeeTableQuery.to[List].result}

  def update(email: String, name: String): Future[Int] = {
    val query = employeeTableQuery.filter(_.email === email)
      .map(_.name).update(name)

    dBComponent.db.run(query)
  }
}

object EmployeeRepo extends EmployeeRepo with DBComponent
