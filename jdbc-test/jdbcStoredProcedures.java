
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;


/*
	Author: Rehna Anthru
	Sub: Assignment on JDBC: update and delete
	Date: 04/01/2022
 */



public class jdbcStoredProcedures {
	static Connection myConn = null;
	static ResultSet myRs=null;
	static CallableStatement myStmt = null;
	static String dbUrl="jdbc:mysql://localhost:3306/demo";
	static String user = "student";
	static String pass = "student";

	public static void main(String[] args) throws Exception {
		
		String theDept="Engineering";
		getEmployeeCountByDept(theDept);
		getEmloyeesByDept(theDept);
		greetTheDept(theDept);
		salaryIncreaseByDept(theDept, 1000);
	}

	public static void getEmployeeCountByDept(String theDept) throws Exception {
		try {

			// This will load the MySQL driver, each DB has its own driver
			myConn =DriverManager.getConnection(dbUrl, user, pass);
			// PreparedStatements can use variables and are more efficient
			myStmt = myConn
					.prepareCall("{call get_count_for_department(?, ?)}");

			// Parameters start with 1
			myStmt.setString(1, theDept);
			myStmt.registerOutParameter(2, Types.INTEGER);
			System.out.println("Calling stored procedure.  get_count_for_department('" + theDept + "', ?)");
			myStmt.execute();

			System.out.println( "Finished calling stored procedure");
			// Get the value of the OUT parameter
			int theCount = myStmt.getInt(2);

			System.out.println("\nThe count = " + theCount);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	public static void getEmloyeesByDept(String theDept) throws Exception {
		try {

			// This will load the MySQL driver, each DB has its own driver
			myConn =DriverManager.getConnection(dbUrl, user, pass);
			// PreparedStatements can use variables and are more efficient
			myStmt = myConn
					.prepareCall("{call get_employees_for_department(?)}");

			// Parameters start with 1
			myStmt.setString(1, theDept);
			System.out.println("Calling stored procedure.  get_employees_for_department('\" + theDepartment + \"')");
			myStmt.execute();

			System.out.println( "Finished calling stored procedure");
			// Get the value of the OUT parameter
			myRs=myStmt.getResultSet();
			displayData(myRs);


		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	public static void greetTheDept(String theDept) throws Exception {
		try {

			// This will load the MySQL driver, each DB has its own driver
			myConn =DriverManager.getConnection(dbUrl, user, pass);
			// PreparedStatements can use variables and are more efficient
			myStmt = myConn
					.prepareCall("{call greet_the_department(?)}");

			// Parameters start with 1
			myStmt.registerOutParameter(1, Types.VARCHAR);
			myStmt.setString(1, theDept);
			System.out.println("Calling stored procedure.  greet_the_department('\" + theDepartment + \"')");
			myStmt.execute();

			System.out.println( "Finished calling stored procedure");
			// Get the value of the INOUT parameter
			String theResult = myStmt.getString(1);
			System.out.println("\nThe result = " + theResult);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	public static void salaryIncreaseByDept(String theDept, double salIncrease ) throws Exception {
		try {

			// This will load the MySQL driver, each DB has its own driver
			myConn =DriverManager.getConnection(dbUrl, user, pass);
			// PreparedStatements can use variables and are more efficient
			myStmt = myConn
					.prepareCall("{call increase_salaries_for_department(?, ?)}");

			// Set the parameters
			myStmt.setString(1, theDept);
			myStmt.setDouble(2, salIncrease);
			
			// Call stored procedure
			System.out.println("\\n\\nCalling stored procedure.  increase_salaries_for_department('\" + theDepartment + \"', \" + theIncreaseAmount + \")");
			myStmt.execute();
			System.out.println( "Finished calling stored procedure");
			
			// Show salaries AFTER
			System.out.println("\n\nSalaries AFTER\n");
			getEmloyeesByDept(theDept);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	private static void displayData(ResultSet myRs) throws SQLException {
		// Process result set
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");

			System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
		}

	}
	private static void close() {
		// TODO Auto-generated method stub

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception e) {

		}

	}		

}

