package com.root.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Order;

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
	@Order(1)
	public void InsertClientSuccessful() throws ClassNotFoundException, SQLException
	{
				
		clientService = new ClientServiceIMPL();
		int actual = clientService.insertClient(client);
		
		int expected = 1;
		assertEquals(expected,actual);
	}
	
	//Testing for updating client when client updated successfully from the database
	@Test
	@Order(2)
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
	@Order(3)
	public void SetClientBalanceSuccess() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		int actual = clientService.setBalance(333, 60000, 20000);
		int expected = 1;
		assertEquals(expected,actual);
	}
	
	
	//Testing for showing client balance 
	@Test
	@Order(4)
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
	@Order(5)
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
	@Order(6)
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
	@Order(7)
	public void totalInvestmentSuccessful() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		int actual = clientService.totalInvestment(102);
		int expected = 14800;
		assertEquals(expected,actual);
	}
	
	//'105', 'abc', 'abc@gmail.com', 'abc', '457958', 'bombay', '52000', '0'
	
	//have to take another entry because delete method is not executing in mentioned order
	@Test
	@Order(8)
	public void deleteClientSuccess() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		int actual = clientService.deleteClient("abc@gmail.com");
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
	@Order(9)
	public void UpdateClientFailed() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		ClientModel clientUpdate = new ClientModel();
		client.setClientId(101);
		client.setClientName("Max");
		client.setClientEmail("Max@gmail.com");
		client.setClientPassword("MCA");
		client.setClientPhone("784512");
		client.setClientAddress("Paris"); 
		
		int actual = clientService.updateClient(clientUpdate);
		int expected = 1;
		assertNotEquals(expected,actual);
	}
	
	//Setting client balance after investment successfully (if client exist)
	@Test
	@Order(10)
	public void SetClientBalanceFailed() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		int actual = clientService.setBalance(33, 60000, 20000);
		int expected = 1;
		assertNotEquals(expected,actual);
	}
	
	
	//Testing for showing client balance 
	@Test
	@Order(11)
	public void ClientShowBalanceFailed() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();	
		ClientModel clientNew = clientService.showBalance("Jo@gmail.com");
		int actual = clientNew.getClientBalance();
		int expected = 60000;
		assertNotEquals(expected, actual);
	}
	
	//View Client Successful if client exist
	@Test
	@Order(12)
	public void ViewClientFailed() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		ClientModel clientNew = clientService.viewClient("Jo@gmail.com");
		
		String actualName = clientNew.getClientName();
		String expectedName = "JohnCena";
		
		assertNotEquals(expectedName,actualName);
	}
	
	//Reading client successful if client exist
	@Test
	@Order(13)
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
	
	@Test
	@Order(13)
	public void totalInvestmentFailed() throws ClassNotFoundException, SQLException
	{
		clientService = new ClientServiceIMPL();
		int actual = clientService.totalInvestment(102);
		int expected = 13800;
		assertNotEquals(expected,actual);
	}
	
	 @Test 
	 @Order(14)
	 public void deleteClientFailed() throws ClassNotFoundException, SQLException 
	 { 
		 clientService = new ClientServiceIMPL(); 
		 int actual = clientService.deleteClient("abc@gmail.com"); 
		 int expected = 1;
		 assertNotEquals(expected,actual); 
	 }
	 	
}
