package com.root.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;
import com.root.dao.InvestmentDAO;
import com.root.model.InvestmentDetails;
import com.root.utilities.DbUtilities;

public class InvestmentDaoIMPL implements InvestmentDAO {

	static final Logger LOGGER = Logger.getLogger(InvestmentDaoIMPL.class); 
	private static Connection connection = null;
	private static PreparedStatement st = null;
	private static Statement statement = null;
	
	//this function is to insert the investment or sell details in investmentDetails
	@Override
	public void investmentDetails(InvestmentDetails investment) throws ClassNotFoundException, SQLException {
 		LOGGER.info("Inside investmentDetails from InvestMentDAOIMPL");
		try 
		{
			
			int clientId = investment.getClientModel().getClientId();
			int cryptoId = investment.getCryptoCurrency().getCryptoId();
			int investmentAmout = investment.getInvestment();
			java.util.Date date=new java.util.Date();		
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			
			String select = "select * from investmentdetails where clientId="+clientId+" and cryptoId="+cryptoId+" ";
			ResultSet rs = statement.executeQuery(select);
			
			//if client has already invested then update query will be executed
			if(rs.next())
			{
				String update = "update cryptocurrencymanagementsystem.investmentdetails set date='"+sqlDate+"',investment=investment+"+investmentAmout+" "
						+ "where cryptoId="+cryptoId+" and clientId="+clientId+" ";
				statement.executeUpdate(update);
			}
			
			//if client hasn't invested then new entry will be inserted in the database
			else
			{			
				//insert into investmentdetails (date,cryptoId,clientId,investment) values(STR_TO_DATE('1-01-2012', '%d-%m-%Y'),101,101,10000);
				String insert = "insert into investmentdetails (date,cryptoId,clientId,investment) values(?,?,?,?)";

				st = (PreparedStatement) connection.prepareStatement(insert);
			
				st.setDate(1, sqlDate);
				st.setInt(2, cryptoId);
				st.setInt(3, clientId);
				st.setInt(4, investmentAmout);
				
				st.executeUpdate();
			}
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			LOGGER.info("Something went wrong in Inside investmentDetails from InvestMentDAOIMPL");
			throw e;
		}
		finally
		{
			DbUtilities.getDbConnectionClosed();
		}
		
	}
	
	//this function is to get the total investment of the client invested in particular cryptocurrency
	@Override
	public int totalInvest(int cryptoId, int clientId) throws ClassNotFoundException, SQLException {
		
		LOGGER.info("Inside totalInvest from InvestMentDAOIMPL");
		int totalInvest = 0;
		String getTotalInvest = "select investment from cryptocurrencymanagementsystem.investmentdetails "
				+ "where cryptoId="+cryptoId+" and clientId="+clientId+"";
		
		connection = DbUtilities.getDbConnection();
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(getTotalInvest);
		if(rs.next())
		{
			LOGGER.info("totalInvest executes from InvestMentDAOIMPL");
			totalInvest = rs.getInt(1);
		}
		return totalInvest;
	}
	
	
	
}
