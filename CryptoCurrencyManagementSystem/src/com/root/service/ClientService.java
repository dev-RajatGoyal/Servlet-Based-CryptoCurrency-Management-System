package com.root.service;

import java.sql.SQLException;
import java.util.List;

import com.root.model.ClientModel;

public interface ClientService {

	int insertClient(ClientModel clientModel) throws ClassNotFoundException,SQLException;
	int updateClient(ClientModel clientModel) throws ClassNotFoundException,SQLException;
	List<ClientModel> readClient() throws ClassNotFoundException,SQLException;
	int deleteClient(String clientEmail) throws ClassNotFoundException ,SQLException;
	ClientModel showBalance(String clientEmail) throws ClassNotFoundException,SQLException;
	ClientModel viewClient(String clientEmail) throws ClassNotFoundException,SQLException;
	int setBalance(int id, int balance, int investment) throws ClassNotFoundException,SQLException;
	int totalInvestment(int id) throws ClassNotFoundException,SQLException;
}
