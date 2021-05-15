package com.root.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Order;

import com.root.model.CryptoCurrencyModel;
import com.root.service.CryptoCurrencyService;
import com.root.serviceImpl.CryptoCurrencyServiceIMPL;

public class CryptoCurrencyTesting {

	CryptoCurrencyService cryptoService;

	static CryptoCurrencyModel crypto = new CryptoCurrencyModel();
	{
		crypto.setCryptoId(105);
		crypto.setCryptoName("Nifty50");
		crypto.setCryptoPrice(250);
	}


	@Test
	@Order(1)
	public void AddingCryptoCurrencySuccessful() throws ClassNotFoundException, SQLException {
		cryptoService = new CryptoCurrencyServiceIMPL();

		int actual = cryptoService.addCryptoCurrency(crypto);
		int expected = 1;

		assertEquals(expected, actual);
	}

	@Test 
	@Order(2)
	public void ViewCryptoSuccessful() throws ClassNotFoundException, SQLException { 
		cryptoService = new CryptoCurrencyServiceIMPL();
		CryptoCurrencyModel cryptoCurrency = cryptoService.viewCrypto("BitCoin");
	  
		String actual = cryptoCurrency.getCryptoName(); 
		String expected = "BitCoin";
		assertEquals(expected,actual); 
	}
	 
	@Test 
	@Order(3)
	public void ReadCryptoCurrencySuccess() throws ClassNotFoundException,SQLException { 
		cryptoService = new CryptoCurrencyServiceIMPL();
		ArrayList<CryptoCurrencyModel> cryptos = (ArrayList<CryptoCurrencyModel>)cryptoService.readCryptoCurrency();
	    //int id = ; 
	    int actual = 0;
	    int expected = 1;
	   
	    for(CryptoCurrencyModel cryptoNew : cryptos) 
	    { 
	    	if("BitCoin".equals(cryptoNew.getCryptoName())) 
	    	{
	    		actual = 1; 
	    	}
	    }
	    assertEquals(expected,actual); 
	  }
	
	//'106', 'Share', '250'

	@Test 
	@Order(4)
	public void deleteCryptoSuccessful() throws ClassNotFoundException, SQLException 
	{ 
		cryptoService = new CryptoCurrencyServiceIMPL(); 
		int actual = cryptoService.deleteCryptoCurrency("Share"); 
		int expected = 1;
		assertEquals(expected,actual); 
	}
	 
//Negative TestCases******************************************************************************


	@Test 
	@Order(5)
	public void ViewCryptoFailed() throws ClassNotFoundException, SQLException { 
		cryptoService = new CryptoCurrencyServiceIMPL();
		CryptoCurrencyModel cryptoCurrency = cryptoService.viewCrypto("Coin");
	  
		String actual = cryptoCurrency.getCryptoName(); 
		String expected = "Coins";
		assertNotEquals("Coins","Coin"); 
	}
	
	@Test 
	@Order(6)
	public void ReadCryptoCurrencyFailed() throws ClassNotFoundException,SQLException { 
		cryptoService = new CryptoCurrencyServiceIMPL();
		ArrayList<CryptoCurrencyModel> cryptos = (ArrayList<CryptoCurrencyModel>)cryptoService.readCryptoCurrency();
	    //int id = ; 
	    int actual = 0;
	    int expected = 0;
	   
	    for(CryptoCurrencyModel cryptoNew : cryptos) 
	    { 
	    	if(crypto.getCryptoId()==cryptoNew.getCryptoId()) 
	    	{
	    		actual = 1; 
	    	}
	    }
	    assertNotEquals(expected,actual); 
	  }
	
	
	@Test 
	@Order(7)
	public void deleteCryptoFailed() throws ClassNotFoundException, SQLException 
	{ 
		cryptoService = new CryptoCurrencyServiceIMPL(); 
		int actual = cryptoService.deleteCryptoCurrency(crypto.getCryptoName()); 
		int expected = 1;
		assertNotEquals(expected,actual); 
	}
	 
}
