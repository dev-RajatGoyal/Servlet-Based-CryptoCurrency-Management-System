package com.root.service;

import java.sql.SQLException;

import com.root.model.InvestmentDetails;

public interface InvestmentService {

	void investmentDetails(InvestmentDetails investment) throws ClassNotFoundException,SQLException;
	int totalInvest(int cryptoId, int clientId) throws ClassNotFoundException,SQLException;
}
