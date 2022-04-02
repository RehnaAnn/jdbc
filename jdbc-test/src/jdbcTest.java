import java.sql.*;
/*
Author: Rehna Anthru
Sub: Assignment on JDBC
Date: 04/01/2022
*/

public class jdbcTest {

	public static void main(String[] args) {
		Connection myConn = null;
		Statement myStmt=null;
		ResultSet myRs=null;
		String dbUrl="jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pass = "student";

		try {
			//connecting to database
			myConn =DriverManager.getConnection(dbUrl, user, pass);

			System.out.println("Database Connected");
			//create a statement
			myStmt = myConn.createStatement();
			//execute SQL query
			myRs=myStmt.executeQuery("select * from employees");

			//process the result set
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
