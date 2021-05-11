package com.root.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.root.dao.CryptoCurrencyDAO;
import com.root.model.ClientModel;
import com.root.model.CryptoCurrencyModel;
import com.root.utilities.DbUtilities;

public class CryptoCurrencyDaoIMPL implements CryptoCurrencyDAO {

	static final Logger LOGGER = Logger.getLogger(CryptoCurrencyDaoIMPL.class); 
	private Connection connection = null;
	private Statement statement = null;
	
	//this function is to add the cryptocurrency in database
	@Override
	public int addCryptoCurrency(CryptoCurrencyModel cryptoModel) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside AddCryptoCurrency method from CryptoCurrecnyDaoIMPL");
		int flag = 0;
		try
		{
			String addCrypto = "insert into cryptocurrency (cryptoId,cryptoName,cryptoPrice) values('"+cryptoModel.getCryptoId()+"',"
			+ "'"+cryptoModel.getCryptoName()+"','"+cryptoModel.getCryptoPrice()+"') ";
			
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			flag = statement.executeUpdate(addCrypto);
		}
		catch(Exception e)
		{
			LOGGER.info("Something went wrong in AddCryptoCurrency method from CryptoCurrecnyDaoIMPL");
			throw e;
		}
		finally 
		{
			DbUtilities.getDbConnectionClosed();
		}
		
		return flag;
	}

	//this function is to get the all cryptocurrency details stored in database
	@Override
	public List<CryptoCurrencyModel> readCryptoCurrency() throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside readCryptoCurrency method from CryptoCurrecnyDaoIMPL");

		List<CryptoCurrencyModel> listofCryptos = new ArrayList<CryptoCurrencyModel>();
		try {
			
			String readCrypto = "select * from cryptocurrency";
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(readCrypto);
			while(rs.next())
			{
				CryptoCurrencyModel cryptoCurrency = new CryptoCurrencyModel(rs.getInt("cryptoId"), rs.getString("cryptoName"), 
						rs.getInt("cryptoPrice"));
				
				listofCryptos.add(cryptoCurrency);
			}
			
		}
		catch(Exception e)
		{
			LOGGER.info("Something went wrong in readCryptoCurrency method from CryptoCurrecnyDaoIMPL");
			throw e;
		}
		finally {
			DbUtilities.getDbConnectionClosed();
		}

		return listofCryptos;
	}

	//this function is to delete the particular cryptocurrency
	@Override
	public int deleteCryptoCurrency(String cryptoName) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside deleteCryptoCurrency method from CryptoCurrecnyDaoIMPL");

		int flag = 0;
		try
		{		
			String deleteClient = "delete from cryptocurrency where cryptoName='"+cryptoName+"' ";			
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			flag = statement.executeUpdate(deleteClient);	
		}
		catch(Exception e)
		{
			LOGGER.info("Something went wrong in deleteCryptoCurrency method from CryptoCurrecnyDaoIMPL");
			throw e;
		}
		finally
		{
			DbUtilities.getDbConnectionClosed();
		}
		return flag;
	}

	//this function is get the particular cryptocurrency to calculate the total coins invested or sell in cryptocurrency
	@Override
	public CryptoCurrencyModel viewCrypto(String name) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside viewCrypto method from CryptoCurrecnyDaoIMPL");

		CryptoCurrencyModel crypto = new CryptoCurrencyModel();
		try
		{
			String query = "select * from cryptocurrency  where cryptoName='"+name+"'";
			connection = DbUtilities.getDbConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next())
			{
				crypto = new CryptoCurrencyModel(rs.getInt(1), rs.getString(2), rs.getInt(3));	
			}
			
		}
		catch(Exception e)
		{
			LOGGER.info("Something went wrong in viewCrypto method from CryptoCurrecnyDaoIMPL");
			throw e;
		}
		finally
		{
			DbUtilities.getDbConnectionClosed();
		}

		return crypto;
	}

	
}
