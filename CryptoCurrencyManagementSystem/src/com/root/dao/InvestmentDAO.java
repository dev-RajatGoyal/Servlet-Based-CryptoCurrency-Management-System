package com.root.dao;

import java.sql.SQLException;

import com.root.model.InvestmentDetails;

public interface InvestmentDAO {

	void investmentDetails(InvestmentDetails investment) throws ClassNotFoundException,SQLException;
	int totalInvest(int cryptoId, int clientId) throws ClassNotFoundException,SQLException;
}
