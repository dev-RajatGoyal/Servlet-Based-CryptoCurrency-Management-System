package com.root.service;

import java.sql.SQLException;
import java.util.List;

import com.root.model.CryptoCurrencyModel;

public interface CryptoCurrencyService {

	int addCryptoCurrency(CryptoCurrencyModel cryptoModel) throws ClassNotFoundException,SQLException;
	List<CryptoCurrencyModel> readCryptoCurrency() throws ClassNotFoundException,SQLException;
	int deleteCryptoCurrency(String cryptoName) throws ClassNotFoundException,SQLException;
	CryptoCurrencyModel viewCrypto(String name) throws ClassNotFoundException,SQLException;
	
}
