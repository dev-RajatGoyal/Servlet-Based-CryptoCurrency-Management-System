package com.root.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtilities {
	
	public static final String url = "jdbc:mysql://localhost:3306/cryptocurrencymanagementsystem";
	public static final String userName = "root";
	public static final String password = "root";
	public static Connection connection = null;
	
	public static Connection getDbConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url,userName,password);
		
		return connection;
	}
	
	public static void getDbConnectionClosed() throws SQLException
	{
		if(connection!=null)
		{
			connection.close();
		}
		
	}
}
