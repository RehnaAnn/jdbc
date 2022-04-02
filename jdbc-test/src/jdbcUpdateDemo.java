import java.sql.*;

/*
Author: Rehna Anthru
Sub: Assignment on JDBC: update and delete
Date: 04/01/2022
*/



public class jdbcUpdateDemo {
	static Connection myConn = null;
	static Statement myStmt=null;
	static ResultSet myRs=null;

	static String dbUrl="jdbc:mysql://localhost:3306/demo";
	static String user = "student";
	static String pass = "student";

	public static void main(String[] args) {

		

		try {
			//1. connecting to database
			myConn =DriverManager.getConnection(dbUrl, user, pass);

			System.out.println("Database Connected");

			//2. create a statement
			myStmt = myConn.createStatement();
			
			//Call helper method to display the employees info
			System.out.println("BEFORE THE UPDATE....");
			displayEmployee(myConn, "John", "Doe");

			//3. Update the employee
			System.out.println("\nEXECUTING THE UPDATE FOR: John Doe\n");

			int rowsAffected = myStmt.executeUpdate("update employees set email='john.doe@luv2code.com' where last_name='Doe' and first_name='John'");

			//4. process the result set
			System.out.println("AFTER THE UPDATE...");
			displayEmployee(myConn, "John", "Doe");
			
			rowsAffected = myStmt.executeUpdate("delete from employees where last_name='Doe' and first_name='John'");

			//4. process the result set
			System.out.println("AFTER THE DELETE...");
			displayEmployee(myConn, "John", "Doe");
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			close();
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

	private static void displayEmployee(Connection myConn, String firstName, String lastName) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("select * from employees where first_name= '" + firstName + "' and last_name = '"+ lastName + "'");
		try {
			myConn =DriverManager.getConnection(dbUrl, user, pass);
			// ResultSet is initially before the first data set
			myStmt = myConn
					.createStatement();
			myRs = myStmt.executeQuery("select * from employees where first_name= '" + firstName + "' and last_name = '"+ lastName + "'");
			while(myRs.next()) {
				System.out.println(myRs.getString("first_name") + " " + myRs.getString("last_name") + " " + myRs.getString("email"));
			}

		} catch (Exception e) {
			throw e;
			
		}
		
	}

}



