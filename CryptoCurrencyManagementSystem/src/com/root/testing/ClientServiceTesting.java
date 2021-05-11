package com.root.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.root.model.ClientModel;
import com.root.service.ClientService;
import com.root.serviceImpl.ClientServiceIMPL;

public class ClientServiceTesting {
	
	ClientService clientService; 
	
	static ClientModel client = new ClientModel();
	{
		client.setClientId(333);
		client.setClientName("John");
		client.setClientEmail("John@gmail.com");
		client.setClientPassword("john123");
		client.setClientPhone("7894561");
		client.setClientAddress("Canada");
		client.setClientBalance(80000);
		client.setClientInvestment(0);
	}
	
	@Test
	public void InsertClientSuccessful() throws ClassNotFoundException, SQLException
	{
				
		clientService = new ClientServiceIMPL();
		int actual = clientService.insertClient(client);
		
		int expected = 1;
		assertEquals(expected,actual);
	}
	
	//Testing for updating client when client updated successfully from the database
	@Test
	public void UpdateClientSuccessful() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		
		client.setClientName("JohnCena");
		client.setClientPassword("MCA");
		client.setClientPhone("784512");
		client.setClientAddress("Paris"); 
		
		int actual = clientService.updateClient(client);
		int expected = 1;
		assertEquals(expected,actual);
	}
	
	//Setting client balance after investment successfully (if client exist)
	@Test
	public void SetClientBalanceSuccess() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		int actual = clientService.setBalance(333, 60000, 20000);
		int expected = 1;
		assertEquals(expected,actual);
	}
	
	
	//Testing for showing client balance 
	@Test
	public void ClientShowBalanceSuccessful() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();	
		ClientModel client = clientService.showBalance("John@gmail.com");
		int actual = client.getClientBalance();
		int expected = 60000;
		assertEquals(expected, actual);
	}
	
	//View Client Successful if client exist
	@Test
	public void ViewClientSuccessful() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		ClientModel clientNew = clientService.viewClient("John@gmail.com");
		
		String actualName = clientNew.getClientName();
		String expectedName = client.getClientName();
		
		assertEquals(expectedName,actualName);
	}
	
	//Reading client successful if client exist
	@Test
	public void ReadClientSuccess() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		ArrayList<ClientModel> list =  (ArrayList<ClientModel>) clientService.readClient();
		int id = client.getClientId();
		int actual = 0;
		int expected = 1;
 
		for(ClientModel client : list)
		{
			if(id==client.getClientId())
			{
				actual = 1;
			}
		} 
		
		assertEquals("Read Client Successfully tested",expected,actual);
	}

	@Test
	public void deleteClientSuccess() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		int actual = clientService.deleteClient("John@gmail.com");
		int expected = 1;
		assertEquals(expected,actual);
	}
	  
//NEGATIVE TEST CASES****************************************************************************************
	
	/*
	 * @Test public void InsertClientFailed() throws ClassNotFoundException,
	 * SQLException { clientService = new ClientServiceIMPL(); int actual =
	 * clientService.insertClient(client);
	 * 
	 * int expected = 1; assertEquals(expected,actual); }
	 */
	
	//Testing for updating client when client updated successfully from the database
	@Test
	public void UpdateClientFailed() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		
		client.setClientName("JohnCena");
		client.setClientPassword("MCA");
		client.setClientPhone("784512");
		client.setClientAddress("Paris"); 
		
		int actual = clientService.updateClient(client);
		int expected = 1;
		assertNotEquals(expected,actual);
	}
	
	//Setting client balance after investment successfully (if client exist)
	@Test
	public void SetClientBalanceFailed() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		int actual = clientService.setBalance(333, 60000, 20000);
		int expected = 0;
		assertNotEquals(expected,actual);
	}
	
	
	//Testing for showing client balance 
	@Test
	public void ClientShowBalanceFailed() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();	
		ClientModel client = clientService.showBalance("John@gmail.com");
		int actual = client.getClientBalance();
		int expected = 50000;
		assertNotEquals(expected, actual);
	}
	
	//View Client Successful if client exist
	@Test
	public void ViewClientFailed() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		ClientModel clientNew = clientService.viewClient("John@gmail.com");
		
		String actualName = clientNew.getClientName();
		String expectedName = "JohnCena";
		
		assertNotEquals(expectedName,actualName);
	}
	
	//Reading client successful if client exist
	@Test
	public void ReadClientFailed() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		ArrayList<ClientModel> list =  (ArrayList<ClientModel>) clientService.readClient();
		int id = client.getClientId();
		int actual = 0;
		int expected = 0;

		for(ClientModel client : list)
		{
			if(id==client.getClientId())
			{
				actual = 1;
			}
		}
		
		assertNotEquals("Read Client Successfully tested",expected,actual);
	}
	
	/*
	 * @Test public void deleteClientFailed() throws ClassNotFoundException,
	 * SQLException { clientService = new ClientServiceIMPL(); int actual =
	 * clientService.deleteClient("John@gmail.com"); int expected = 1;
	 * assertNotEquals(expected,actual); }
	 */
	
}
