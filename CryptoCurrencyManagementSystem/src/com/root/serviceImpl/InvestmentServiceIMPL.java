package com.root.serviceImpl;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.root.dao.InvestmentDAO;
import com.root.daoImpl.InvestmentDaoIMPL;
import com.root.model.InvestmentDetails;
import com.root.service.InvestmentService;

public class InvestmentServiceIMPL implements InvestmentService{

	static final Logger LOGGER = Logger.getLogger(InvestmentServiceIMPL.class);
	private InvestmentDAO invest = new InvestmentDaoIMPL();

	@Override
	public void investmentDetails(InvestmentDetails investment) throws ClassNotFoundException, SQLException {
		LOGGER.info("inside investmetnDetails from InvestmentServiceIMPL ");
		invest.investmentDetails(investment);
		
	}

	@Override
	public int totalInvest(int cryptoId, int clientId) throws ClassNotFoundException, SQLException {
		LOGGER.info("inside totalInvest from InvestmentServiceIMPL ");
		return invest.totalInvest(cryptoId, clientId);
	}
	
}
