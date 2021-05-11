package com.root.dao;

import java.sql.SQLException;
import java.util.List;

import com.root.model.ClientModel;

public interface ClientDAO {
	
	//Tested but needs to re check
	int insertClient(ClientModel clientModel) throws ClassNotFoundException,SQLException;
	
	//Tested
	int updateClient(ClientModel clientModel) throws ClassNotFoundException,SQLException;
		
	//Tested
	int setBalance(int id, int balance, int investment) throws ClassNotFoundException,SQLException;
	
	//Tested
	ClientModel showBalance(String clientEmail) throws ClassNotFoundException,SQLException;
		
	//Tested
	ClientModel viewClient(String clientEmail) throws ClassNotFoundException,SQLException;
		
	//Tested
	List<ClientModel> readClient() throws ClassNotFoundException,SQLException;
	
	//Tested
	int deleteClient(String clientEmail) throws ClassNotFoundException,SQLException;
		
	//Tested
	int totalInvestment(int id) throws ClassNotFoundException,SQLException;
		
		
}
