package com.root.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.root.controller.AddCryptoCurrency;
import com.root.dao.AdminDAO;
import com.root.model.AdminModel;
import com.root.utilities.DbUtilities;

public class AdminDaoIMPL implements AdminDAO{
	 
	static final Logger LOGGER = Logger.getLogger(AdminDaoIMPL.class); 
	
	private static Connection connection = null;
	private static Statement statement = null;
	
	@Override
	public List<AdminModel> readAdmin(String adminEmail, String adminPassword) throws ClassNotFoundException, SQLException {
		
		LOGGER.info("Inside readAdmin Method from AdminDaoIMPL");
		
		//fetching the details of the admin from the database
		List<AdminModel> listofAdmins = new ArrayList<AdminModel>();
		try
		{
			String readAdmin = "select * from admin";
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(readAdmin);
			if(rs!=null)
			{
				LOGGER.info("Admin details fetched successfully");
			}
			while(rs.next())
			{
				
				AdminModel admin = new AdminModel(rs.getInt("adminId"), rs.getString("adminName"), rs.getString("adminEmail"), rs.getString("adminPassword"));
				listofAdmins.add(admin);
			}
			
		}
		catch(Exception e) {
			LOGGER.info("Something went wrong while fetching the data in readAdmin method from AdminDaoIMPL");
			throw e;
		}
		finally
		{
			DbUtilities.getDbConnectionClosed();
		}
		LOGGER.info("readAdmin method from AdminDaoImpl terminated");
		return listofAdmins;
	
	}
	
}
