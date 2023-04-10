package accountdatabase;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseControl
{
	private Connection conn;

	//Add any other data fields you like – at least a Connection object is mandatory
	public DatabaseControl() throws IOException
	{

		//Read properties file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("DatabaseControl/db.properties");
		prop.load(fis);
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password"); 

		try
		{
			//Read the connection properties as Strings


			//Perform the Connection
			conn = DriverManager.getConnection(url,user,pass);

			//conn.close();  

			System.out.println("Success");
		} 
		catch (SQLException e)
		{

			e.printStackTrace();
		} 
	}

	public ArrayList<String> query(String query)
	{
		//Add your code here

		Statement stmt;
		ResultSet rs;
		ResultSetMetaData rmd;
		int rowCount = 0;
		ArrayList<String> array = new ArrayList<String>();
		try 
		{
			stmt = conn.createStatement();

			StringBuffer queryBuilder = new StringBuffer();
			queryBuilder.append("select ");
			queryBuilder.append(query);
			queryBuilder.append(" from user");
			//Execute a query
			rs=stmt.executeQuery(queryBuilder.toString());  

			//Get metadata about the query
			rmd = rs.getMetaData();

			//Get the # of columns
			int no_columns = rmd.getColumnCount();

			//Get a column name
			String name = rmd.getColumnName(1);

			//Fetch each row (use numeric numbering
			while(rs.next()) 
			{
				rowCount++;
				StringBuffer record = new StringBuffer();
				for (int i = 0; i < no_columns; i++)
				{
					String value = rs.getString(i+1); 
					record.append(value);
					record.append(",");
					array.add(record.toString());
				}
			}

			if (rowCount > 0)
			{
				return array;
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

	public void executeDML(String dml) throws SQLException
	{
		//Add your code here
		Statement stmt;
		//Create a statement
		stmt=conn.createStatement();  

		//Execute a DML statement
		stmt.execute(dml);

	}

	/*
	 * Query for username to see if they exist already. 
	 * If they do, return false for failure do to pre-existing account.
	 * If they don't, return true
	 */
	public boolean verifyAccount(String username)
	{
		//Expecting ArrayList of Strings returned from query
		ArrayList<String> queryResult = this.query("username");
		
		if (queryResult.contains(username))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/*
	 * Use executeDML to insert new entry
	 * Returns true if account creation successful
	 * Returns false if account creation failed
	 */
	public boolean createNewAccount(String username, String password) {
		//Set up the statement that gets sent to the database
		//Should take the following form:
		// insert into user values("username","password",AES_ENCRYPT("password","16"));
		StringBuffer insertStatement = new StringBuffer();
		insertStatement.append("insert into user values(\"");
		insertStatement.append(username);
		insertStatement.append("\",\"");
		insertStatement.append(password);
		insertStatement.append("\",AES_ENCRYPT(\"");
		insertStatement.append(password);
		insertStatement.append("\",\"16\"));");
		
		try
		{
			executeDML(insertStatement.toString());
		}
		catch (SQLException e)
		{
			//Show what went wrong (if anything) 
			e.printStackTrace();
			//False if creation failed
			return false;
		}
		
		//Only gets here if no exception with DML
		return true;
	}
}
