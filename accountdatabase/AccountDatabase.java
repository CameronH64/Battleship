package accountdatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class AccountDatabase {
	private Connection conn;

	public AccountDatabase() throws IOException {

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream("accountdatabase/db.properties");
		properties.load(fileInputStream);

		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String pass = properties.getProperty("password");

		try {
			this.conn = DriverManager.getConnection(url, user, pass);
			Statement stmt = this.conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS accounts (username VARCHAR(255) PRIMARY KEY, password VARCHAR(255))");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void storeAccount(String username, String password) {
		String hashedPassword = hashPassword(password);
		String sql = "INSERT INTO accounts (username, password) VALUES (?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, hashedPassword);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyAccount(String username, String password) {
		String hashedPassword = hashPassword(password);
		String sql = "SELECT password FROM accounts WHERE username = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String storedPassword = rs.getString("password");
				pstmt.close();
				return hashedPassword.equals(hashPassword(storedPassword));
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private String hashPassword(String password) {
		// You can use any hash function here, e.g. SHA-256
		return "hashed_" + password;
	}

	public ArrayList<String> query1(String query)
	{

		int i = 0;
		int rowCount = 0;		// Detect empty result set.
		ArrayList<String> array = new ArrayList<String>();

		// Create a statement from the Connection.
		Statement stmt;

		try {
			stmt = conn.createStatement();

			// Run query
			ResultSet rs = stmt.executeQuery(query);

			// Get the metadata (get the number of columns for the for loop.
			ResultSetMetaData rmd = rs.getMetaData();
			int noColumns = rmd.getColumnCount();

			// Process this stuff
			while(rs.next()){

				rowCount++;			// Find out if you have an empty result set.
				String record = "";

				for (i = 0; i < noColumns; i++) {

					String value = rs.getString(i+1);			// Indexing starts at 1, not 0.
					record += value + ",";
					array.add(record);
					// rowCount++;

				}

			}



			if (rowCount > 0)
				return array;
			else
				return null;



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

		//Add your code here

		// A generic way of getting any query into the system.

		// Returning an arraylist of comma delminated records.
		// Need to use a loop to get this data.

		// Don't know how many rows.
		// Can find number of columns using the metadata.

		/*
		 * 0 Joe Smith, 1215 Main, 329-2511
		 * 1 ...
		 * 2 ...
		 * 
		 * */

	}

	public void executeDML(String dml) throws SQLException
	{
		//Add your code here

		Statement stmt = conn.createStatement();
		//stmt.execute(dml);
		try {
			stmt.executeUpdate(dml);
			System.out.println("DML executed successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> query(String query) 
	{
		Statement stmt;
		ResultSet rs;
		ResultSetMetaData rmd;
		int rowCount = 0;
		ArrayList<String> results = new ArrayList<String>();
		
		try 
		{
			stmt = conn.createStatement();

			//Execute a query
			rs=stmt.executeQuery("select " + query + " from accounts");  

			//Get metadata about the query
			rmd = rs.getMetaData();

			//Get the # of columns
			int no_columns = rmd.getColumnCount();

			//Fetch each row (use numeric numbering
			while(rs.next()) 
			{
				rowCount++;
				StringBuffer record = new StringBuffer();
				for (int i = 0; i < no_columns; i++)
				{
					String value = rs.getString(i+1); 
					results.add(value);
				}
			}

			if (rowCount > 0)
			{
				return results;
			}
			else 
			{
				return null;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		
	}





}