package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Employee;
import com.Salaire;

public class EmployeeDAO {
 private static final String jdbcURL="jdbc:mysql://localhost:3306/empdb";
 private static final String jdbcUser="root";
 private static final String jdbcpassword="";

private static final String Insert = "INSERT INTO employes(NumEmp,Nom,Nombrejour,taux_jounaliere,Salaire) VALUES (?,?,?,?,?)";
private static final String Select_all="SELECT* FROM employes ";
private static final String SalaireMinMax ="SELECT MIN(Salaire) AS Min, MAX(Salaire) AS Max, SUM(Salaire) AS Somme FROM employes";

private  static  final  String Select_Emp=" SELECT* FROM employes WHERE NumEmp=?";
private static final String Delete ="DELETE FROM employes WHERE NumEmp=?";
 private static final String UPDATE = "UPDATE employes SET Nom = ?, Nombrejour = ?, taux_jounaliere = ?,Salaire=?  WHERE NumEmp = ?";
 public EmployeeDAO(){

 }
 //Connection a base de donne
 protected Connection getConnection() {
  Connection connection = null;
  try {
   Class.forName("com.mysql.cj.jdbc.Driver");
   connection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcpassword);
  } catch (SQLException | ClassNotFoundException e) {
   e.printStackTrace();
  }
  return connection;
 }
 //Min max et somme
 public Salaire getSalairesMinMaxSum() {
  Salaire salaire = null;

  try (Connection connection = getConnection();
       PreparedStatement preparedStatement = connection.prepareStatement(SalaireMinMax);
       ResultSet resultSet = preparedStatement.executeQuery()) {

   if (resultSet.next()) {
    double min = resultSet.getDouble("Min");
    double max = resultSet.getDouble("Max");
    double somme = resultSet.getDouble("Somme");

    salaire = new Salaire(min, max, somme);
   }
  } catch (SQLException e) {
   e.printStackTrace(); // Gérer l'exception ou journaliser selon votre besoin
  }

  return salaire;
 }
//Insertion de donnee
public void insertEmployee(Employee employee) {
 try (Connection connection = getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(Insert)) {
  preparedStatement.setInt(1, employee.getNumEmp());
  preparedStatement.setString(2, employee.getNom());
  preparedStatement.setInt(3, employee.getNombrejour());
  preparedStatement.setDouble(4, employee.getTaux_jounaliere());
  preparedStatement.setDouble(5,employee.getSalaire());


  preparedStatement.executeUpdate();
 } catch (SQLException e) {
  e.printStackTrace();
 }
}
//Update employee
public boolean updateEmployee(Employee employee) throws SQLException {
 boolean rowUpdated;
 try (Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(UPDATE)) {
  statement.setString(1, employee.getNom());
  statement.setInt(2, employee.getNombrejour());
  statement.setDouble(3, employee.getTaux_jounaliere());
  statement.setDouble(4,employee.getSalaire());
  statement.setInt(5, employee.getNumEmp());
  System.out.println("Requête SQL UPDATE générée : " + statement.toString());
  rowUpdated = statement.executeUpdate() > 0;
 }
 return rowUpdated;
}
//selevt user by id
//selectall by NumEmp
public List<Employee> selectAllEmployees() {
 List<Employee> employees = new ArrayList<>();

 try (Connection connection = getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(Select_all);
      ResultSet resultSet = preparedStatement.executeQuery()) {

  while (resultSet.next()) {
   int numEmp = resultSet.getInt("NumEmp");
   String nom = resultSet.getString("Nom");
   int nombrejour = resultSet.getInt("Nombrejour");
   double taux_jounaliere = resultSet.getDouble("taux_jounaliere");
   double salaire = resultSet.getDouble("Salaire");

   Employee employee = new Employee(numEmp, nom, nombrejour, taux_jounaliere, salaire);
   employees.add(employee);
  }
 } catch (SQLException e) {
  e.printStackTrace(); // Gérer l'exception ou journaliser selon votre besoin
 }
 System.out.println("EMployee");

 return employees;
}

//Delete Employee
public boolean deleteEmployee(int numEmp) throws SQLException {
 boolean rowDeleted;
 try (Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(Delete)) {
  statement.setInt(1, numEmp);
  rowDeleted = statement.executeUpdate() > 0;
 }
 return rowDeleted;
}

//select user by id
public  Employee selectEmployee(int numEmp) throws SQLException {
 Employee employee = null;

 try (Connection connection = getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(Select_Emp)) {
  preparedStatement.setInt(1, numEmp);

  try (ResultSet resultSet = preparedStatement.executeQuery()) {
   if (resultSet.next()) {
    String nom = resultSet.getString("Nom");
    int nombrejour = resultSet.getInt("Nombrejour");
    double taux_jounaliere = resultSet.getDouble("taux_jounaliere");
    double salaire = resultSet.getDouble("Salaire");

    employee = new Employee(numEmp, nom, nombrejour, taux_jounaliere, salaire);
   }
  }
 }

 return employee;
}

}


