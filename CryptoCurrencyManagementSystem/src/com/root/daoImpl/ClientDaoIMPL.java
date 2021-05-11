package com.root.daoImpl;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.root.dao.ClientDAO;
import com.root.model.ClientModel;
import com.root.utilities.DbUtilities;

public class ClientDaoIMPL implements ClientDAO{

	static final Logger LOGGER = Logger.getLogger(ClientDaoIMPL.class); 
	
	private static Connection connection = null;
	private static PreparedStatement st = null;
	private static Statement statement = null;
	  
	//this function is to insert client details while registering
	@Override
	public int insertClient(ClientModel clientModel) throws ClassNotFoundException, SQLException {
		LOGGER.info("insertClient Method from ClientDaoImpl Executes");
		int flag = 0;
		try 
		{	
			int clientId = clientModel.getClientId();
			String clientName = clientModel.getClientName();
			String clientEmail = clientModel.getClientEmail();
			String clientPassword = clientModel.getClientPassword();
			String clientPhone = clientModel.getClientPhone();
			String clientAddress = clientModel.getClientAddress();
			int clientBalance = clientModel.getClientBalance();
			int clientInvestment = 0;
			
			String str = "insert into client values(?,?,?,?,?,?,?,?)";
			
			connection = DbUtilities.getDbConnection();
			st = connection.prepareStatement(str);
			
			st.setInt(1, clientId);
			st.setString(2, clientName);
			st.setString(3, clientEmail);
			st.setString(4, clientPassword);
			st.setString(5, clientPhone);
			st.setString(6, clientAddress);
			st.setInt(7, clientBalance);
			st.setInt(8, clientInvestment);
			flag = st.executeUpdate();
			LOGGER.info("client insert method executes successfully"); 
		}
		catch(Exception e)
		{
			LOGGER.info("Something went wrong in insertClient method from ClientDaoImpl Executes");
			throw e;
		}
		finally 
		{
			DbUtilities.getDbConnectionClosed();
		}
		return flag;
		
	}
 
	//this function to update existing client details
	@Override
	public int updateClient(ClientModel client) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside updateClient method from ClientDaoIMPL");
		int flag = 0;
		String update = "update client set clientName='"+client.getClientName()+"',"
				+ " clientEmail='"+client.getClientEmail()+"', clientPassword='"+client.getClientPassword()+"', "
				+ "clientPhone='"+client.getClientPhone()+"', clientAddress='"+client.getClientAddress()+"' "
						+ "where clientEmail='"+client.getClientEmail()+"' ";
 
		try {
			LOGGER.info("Client details updated successfully from insertClient method from ClientDaoImpl Executes");
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			flag = statement.executeUpdate(update);
		}
		catch(Exception e)
		{
			LOGGER.info("Something went in updateClient method from ClientDaoIMPL");
			throw e;
		}
		finally
		{
			DbUtilities.getDbConnectionClosed();
		}
		return flag;
	}

	//this function is to fetching all the clients details from the database
	@Override
	public List<ClientModel> readClient() throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside readClient method ");
		List<ClientModel> listofClients = new ArrayList<ClientModel>();
		try {
			String readClient = "select * from client";
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(readClient);
			while(rs.next())
			{
				ClientModel clientModel = new ClientModel(rs.getInt(1), rs.getString(2), 
						rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));
				
				listofClients.add(clientModel);
			}
			LOGGER.info("Reading all clients successfully");
			
		}
		catch(Exception e)
		{
			LOGGER.info("Something went wrong in realClient Method");
			throw e;
		}
		finally {
			DbUtilities.getDbConnectionClosed();
		}
		return listofClients;
	}

	//this function is to delete the client account from the database
	@Override
	public int deleteClient(String clientEmail) throws ClassNotFoundException, SQLException {
		LOGGER.info("inside deleteClient method from CLIENTDAOIMPL");
		int flag = 0;
		try
		{		
			String deleteClient = "delete from client where clientEmail='"+clientEmail+"' ";			
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			flag = statement.executeUpdate(deleteClient);
			LOGGER.info("delete CLient method executes successfully");
		}
		catch(Exception e) 
		{
			LOGGER.info("Something went wrong in deleteClient method from ClientDaoIMPL");
			throw e;
		}
		finally
		{
			DbUtilities.getDbConnectionClosed();
		}
		
		return flag;
	}

	//this function is to show the client Balance
	@Override
	public ClientModel showBalance(String clientEmail) throws ClassNotFoundException, SQLException {
		LOGGER.info("inside deleteClient method from CLIENTDAOIMPL");
		ClientModel client = new ClientModel();
		try 
		{
			
			String showBal = "select * from client where clientEmail = '"+clientEmail+"' ";
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(showBal);
			while(rs.next())
			{
				client = new ClientModel(rs.getInt("clientId"), rs.getString("clientName"), 
				rs.getString("clientEmail"),rs.getString("clientPassword"), 
				rs.getString("clientPhone"),rs.getString("clientAddress"), 
				rs.getInt("clientBalance"), rs.getInt("clientInvestment"));
			}
			
			
		}
		catch(Exception e)
		{
			LOGGER.info("Something went wrong in showBalance method from ClientDaoIMPL");
			throw e;
		}
		finally
		{
			DbUtilities.getDbConnectionClosed();
		}
		return client;
	}
 
	//this function is to fetch the particular client to calculate totalInvestment
	@Override
	public ClientModel viewClient(String clientEmail) throws ClassNotFoundException, SQLException {
		LOGGER.info("inside deleteClient method from CLIENTDAOIMPL");
		ClientModel client = new ClientModel();
		try
		{
			 
			String query = "select * from client where clientEmail='"+clientEmail+"'";
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next())
			{
				client = new ClientModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),rs.getInt(8));
			}
			
		}
		catch(Exception e)
		{
			LOGGER.info("Something went wrong in viewClient method from ClientDaoIMPL");
			throw e;	
		}
		finally
		{
			DbUtilities.getDbConnectionClosed();
		}
		return client;
	}

	//this function is to update the client balance and investment after invest and sell in cryptocurrency
	@Override
	public int setBalance(int id, int balance, int investment) throws ClassNotFoundException, SQLException {
		LOGGER.info("inside deleteClient method from CLIENTDAOIMPL");
		int flag = 0;
		try
		{
			String update = "update client set clientBalance='"+balance+"', clientInvestment='"+investment+"' where clientId='"+id+"'";
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			flag = statement.executeUpdate(update);
		}
		catch(Exception e)
		{
			LOGGER.info("Something went wrong in setBalance method from ClientDaoIMPL");
			throw e;
		}
		finally
		{
			DbUtilities.getDbConnectionClosed();
		}
		
		return flag;
	}

	//this function is to get the sum of investment , invested by client in particular cryptocurrency 
	@Override
	public int totalInvestment(int id) throws ClassNotFoundException, SQLException {
		LOGGER.info("inside totalInvestment method from CLIENTDAOIMPL");
		int total = 0;
		try
		{
			String query = "select clientId, sum(investment) from cryptocurrencymanagementsystem.investmentdetails"
					+ " group by clientId having clientId="+id+" ";
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if(rs.next())
			{
				total = rs.getInt(2);
			}

			LOGGER.info("totalInvestment method executes successfully from CLIENTDAOIMPL");
			
		}
		catch(Exception e)
		{
			LOGGER.info("Something went totalInvestment method from ClientDaoIMPL");
			throw e;
		}
		finally
		{
			DbUtilities.getDbConnectionClosed();
		}
		return total;
	}
  		
}
