package com.root.serviceImpl;

import java.sql.SQLException; 
import java.util.List;

import org.apache.log4j.Logger;

import com.root.dao.CryptoCurrencyDAO;
import com.root.daoImpl.CryptoCurrencyDaoIMPL;
import com.root.model.CryptoCurrencyModel;
import com.root.service.CryptoCurrencyService;

public class CryptoCurrencyServiceIMPL implements CryptoCurrencyService{

	static final Logger LOGGER = Logger.getLogger(CryptoCurrencyServiceIMPL.class);
	private CryptoCurrencyDAO cryptoDao = new CryptoCurrencyDaoIMPL();
	
	@Override
	public int addCryptoCurrency(CryptoCurrencyModel cryptoModel) throws ClassNotFoundException, SQLException {
		LOGGER.info("inside addCryptoCurrency method from CryptoCurrencyServiceIMPL");
		return cryptoDao.addCryptoCurrency(cryptoModel);
	}

	@Override
	public List<CryptoCurrencyModel> readCryptoCurrency() throws ClassNotFoundException, SQLException {
		LOGGER.info("inside readCryptoCurrency method from CryptoCurrencyServiceIMPL");
		return cryptoDao.readCryptoCurrency();
	}

	@Override
	public int deleteCryptoCurrency(String cryptoName) throws ClassNotFoundException, SQLException {
		LOGGER.info("inside deleteCryptoCurrency method from CryptoCurrencyServiceIMPL");
		return cryptoDao.deleteCryptoCurrency(cryptoName);
	}

	@Override
	public CryptoCurrencyModel viewCrypto(String name) throws ClassNotFoundException, SQLException {
		LOGGER.info("inside viewCrypto method from CryptoCurrencyServiceIMPL");
		return cryptoDao.viewCrypto(name);
	}

	
}
