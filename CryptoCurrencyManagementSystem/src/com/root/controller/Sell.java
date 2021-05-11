package com.root.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.root.model.ClientModel;
import com.root.model.CryptoCurrencyModel;
import com.root.model.InvestmentDetails;
import com.root.service.ClientService;
import com.root.service.CryptoCurrencyService;
import com.root.service.InvestmentService;
import com.root.serviceImpl.ClientServiceIMPL;
import com.root.serviceImpl.CryptoCurrencyServiceIMPL;
import com.root.serviceImpl.InvestmentServiceIMPL;


public class Sell extends HttpServlet {
	 
	static final Logger LOGGER = Logger.getLogger(Sell.class); 
	private CryptoCurrencyService cryptoService = new CryptoCurrencyServiceIMPL();
	private ClientService clientService = new ClientServiceIMPL();
	private InvestmentService investmentService = new InvestmentServiceIMPL();
	 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside Sell Servlet");
		response.setContentType("text/html");
		
		HttpSession session = request.getSession(false);
		int coins = (int)session.getAttribute("coins");
		String clientMail = (String)session.getAttribute("clientEmail");
		String cryptoName = (String)session.getAttribute("cryptoName");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		
		try 
		{
			CryptoCurrencyModel crypto = cryptoService.viewCrypto(cryptoName);
			ClientModel client = clientService.viewClient(clientMail);
			
			int sellCoins = Integer.parseInt(request.getParameter("coin"));
			if(sellCoins <= coins)
			{
				int sellAmount = crypto.getCryptoPrice() * sellCoins;
				
				InvestmentDetails invest = new InvestmentDetails();
				invest.setInvestmentDate(date);
				invest.setClientModel(client);
				invest.setCryptoCurrency(crypto);
				invest.setInvestmentDate(date);
				invest.setInvestment(invest.getInvestment() - sellAmount);
				
				investmentService.investmentDetails(invest);
				
				client.setClientBalance(client.getClientBalance() + sellAmount);
				
				int totalInvestment = clientService.totalInvestment(client.getClientId());
				client.setClientInvestment(totalInvestment - sellAmount);
				
				clientService.setBalance(client.getClientId(), client.getClientBalance(), client.getClientInvestment());
				LOGGER.info(client.getClientName()+" successfully sell "+sellCoins+" , "+crypto.getCryptoName());
				out.println("<head><link rel='stylesheet' href='home.css'></head><center><div>"
						+ "<h2>You have successfully sell "+sellCoins+" "+crypto.getCryptoName()+"</h2>"
								+ "<h2>Your total investment is : "+client.getClientInvestment()+"</h2>"
								+ "<h2><a href=\"ClientDashBoard.html\">Go back to DashBoard</a></h2>"
						+ "</div></center>");
			}
			else
			{

				out.println("<head><link rel='stylesheet' href='home.css'></head><center><div>"
						+ "<h2>You can sell upto "+coins+" "+crypto.getCryptoName()+"</h2>"
						+ "<h2><a href=\"ClientDashBoard.html\">Go back to DashBoard</a></h2>"
						+ "<h2><a href=\"Logout.html\">Logout</a></h2>"
						+ "</div></center>");
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			LOGGER.info("Something went wrong from Sell Servlet");
			e.printStackTrace();
		}
		LOGGER.info("Sell Servlet Ends");
		
		
	}

}
