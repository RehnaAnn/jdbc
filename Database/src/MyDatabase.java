import java.sql.*;
/*
Author: Rehna Anthru
Sub: Assignment on JDBC
Date: 04/01/2022
*/
public class MyDatabase {
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private static String connection = "jdbc:mysql://localhost:3306/classicmodels?user=root&password=Kunjuvava12!";


	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		displayData(); 
		insertData();
		displayData(); 
		deleteData();
		displayData();

	}
	public static void insertData() throws Exception {
		try {

			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection(connection);

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("insert into products values (?, ?, ?, ?, ? , ?, ?,?,?)");

			// Parameters start with 1
			preparedStatement.setString(1, "HD2");
			preparedStatement.setString(2, "Harley Davison");
			preparedStatement.setString(3, "Motorcycles");
			preparedStatement.setString(4, "1:10");
			preparedStatement.setString(5, "Mainline Harley");
			preparedStatement.setString(6, "Features include: Turnable front wheels");
			preparedStatement.setInt(7, 100);
			preparedStatement.setDouble(8, 200);
			preparedStatement.setDouble(9, 220);

			preparedStatement.executeUpdate();
			System.out.println("Insert complete");

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}
	public static void deleteData() throws Exception {
		try {

			// Setup the connection with the DB
			connect = DriverManager.getConnection(connection);

			// Remove again the insert comment
			preparedStatement = connect
					.prepareStatement("delete from products where productCode= ? ; ");
			preparedStatement.setString(1, "HD");
			preparedStatement.executeUpdate();
			System.out.println("Delete complete");

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private static void writeMetaData(ResultSet resultSet) throws SQLException {
		//  Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		}
	}

	private static void displayData() throws SQLException {
		try {
			connect = DriverManager.getConnection(connection);
			// ResultSet is initially before the first data set
			preparedStatement = connect
					.prepareStatement("SELECT * from products");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println(resultSet.getString("productCode") + " " + resultSet.getString("productName") + " " + resultSet.getDouble("MSRP"));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}
	private static void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
