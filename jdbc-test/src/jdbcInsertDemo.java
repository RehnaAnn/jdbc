
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * 
Author: Rehna Anthru
Sub: Assignment on JDBC :Insert demo
Date: 04/01/2022
*/


public class jdbcInsertDemo {

		public static void main(String[] args) {
			
			Connection myConn = null;
			Statement myStmt=null;
			ResultSet myRs=null;
			
			String dbUrl="jdbc:mysql://localhost:3306/demo";
			String user = "student";
			String pass = "student";

			try {
				//1. connecting to database
				myConn =DriverManager.getConnection(dbUrl, user, pass);

				System.out.println("Database Connected");
				
				//2. create a statement
				myStmt = myConn.createStatement();
				
				//3. Insert a new employee
				System.out.println("Inserting a new employee to database\n");
				
				int rowsAffected = myStmt.executeUpdate("insert into employees" + "(last_name, first_name, email, department, salary)" + "values" + "('Wright', 'Eric','eric.wright@foo.com', 'HR', 33000.00)");

				//4. verify this by getting a list of employees
				myRs = myStmt.executeQuery("select * from employees order by last_name");
				
				//5. process the result set
				while (myRs.next()) {
					System.out.println(myRs.getString("last_name") + ","+ myRs.getString("first_name"));
				}
			}
			catch(Exception exc) {
				exc.printStackTrace();
			}
			finally {
				if(myRs !=null) {			
				}
				if (myStmt !=null) {

				}
				if(myConn !=null) {

				}
			}
		}

	}

