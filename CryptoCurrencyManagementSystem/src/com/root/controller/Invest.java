package com.root.controller;

import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
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


public class Invest extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(Invest.class); 
	private CryptoCurrencyService cryptoService = new CryptoCurrencyServiceIMPL();
	private ClientService clientService = new ClientServiceIMPL();
	private InvestmentService investmentService = new InvestmentServiceIMPL();
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside Invest Servlet");
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
			
			int buyCoins = Integer.parseInt(request.getParameter("coin"));
			if(buyCoins <= coins)
			{
				
				int investment = crypto.getCryptoPrice() * buyCoins;
				
				InvestmentDetails invest = new InvestmentDetails();
				invest.setInvestmentDate(date);
				invest.setClientModel(client);
				invest.setCryptoCurrency(crypto);
				invest.setInvestmentDate(date);
				invest.setInvestment(investment);
				
				investmentService.investmentDetails(invest);
				
				client.setClientBalance(client.getClientBalance() - investment);
				
				int totalInvestment = clientService.totalInvestment(client.getClientId());
				client.setClientInvestment(totalInvestment);
				
				clientService.setBalance(client.getClientId(), client.getClientBalance(), client.getClientInvestment());
				LOGGER.info(client.getClientName()+" successfully "+investment+" invested in"+crypto.getCryptoName());
				out.println("<link rel=\"stylesheet\" href=\"home.css\"/><center><div>"
						+ "<h2>You have successfully invested "+investment+" in "+crypto.getCryptoName()+"</h2>"
								+ "<h2>Your total investment is : "+totalInvestment+"</h2>"
								+ "<h2><a href=\"ClientDashBoard.html\">Go back to DashBoard</a></h2>"
						+ "</div></center>");
			}
			else
			{
				LOGGER.info(client.getClientName()+" failed to invest in "+crypto.getCryptoName()+". Becuase client trying to invest more than balance.");
				out.println("<head><link rel='stylesheet' href='home.css'></head><center><div>"
						+ "<h2>You can buy upto "+coins+"</h2>"
						+ "<h2><a href=\"ClientDashBoard.html\">Go back to DashBoard</a></h2>"
						+ "<h2><a href=\"Logout.html\">Logout</a></h2>"
						+ "</div></center>");
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			LOGGER.info("Something went wrong from invest servlet");
			e.printStackTrace();
		}
		
		LOGGER.info("Invest servlet ends");
	}

}
