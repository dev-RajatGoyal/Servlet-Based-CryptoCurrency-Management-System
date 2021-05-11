package com.root.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.root.dao.ClientDAO;
import com.root.daoImpl.ClientDaoIMPL;
import com.root.model.ClientModel;
import com.root.service.ClientService;
import com.root.utilities.DbUtilities;

public class ClientServiceIMPL implements ClientService {

	static final Logger LOGGER = Logger.getLogger(ClientServiceIMPL.class); 
	private ClientDAO clientDao = new ClientDaoIMPL();
	 
	@Override
	public int insertClient(ClientModel clientModel) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside insertClient method from ClientServiceIMPL");
		return clientDao.insertClient(clientModel);
	}
 
	@Override
	public int updateClient(ClientModel client) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside updateClient method from ClientServiceIMPL");
		return clientDao.updateClient(client);
		
	}
 
	@Override
	public List<ClientModel> readClient() throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside readClient method from ClientServiceIMPL");
		return clientDao.readClient();
		
	}

	@Override
	public int deleteClient(String clientEmail) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside deleteClient method from ClientServiceIMPL");
		return clientDao.deleteClient(clientEmail);
		
	}

	@Override
	public ClientModel showBalance(String clientEmail) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside showBalance method from ClientServiceIMPL");
		return clientDao.showBalance(clientEmail);
		
	}

	@Override
	public ClientModel viewClient(String clientEmail) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside viewClient method from ClientServiceIMPL");
		return clientDao.viewClient(clientEmail);
	}

	@Override
	public int setBalance(int id, int balance, int investment) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside setBalance method from ClientServiceIMPL");
		 return clientDao.setBalance(id, balance, investment);
	}

	@Override
	public int totalInvestment(int id) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside totalInvestment method from ClientServiceIMPL");
		return clientDao.totalInvestment(id);
	}

}
