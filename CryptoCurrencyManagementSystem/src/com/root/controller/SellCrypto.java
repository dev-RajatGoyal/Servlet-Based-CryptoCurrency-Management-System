package com.root.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.root.model.ClientModel;
import com.root.model.CryptoCurrencyModel;
import com.root.service.ClientService;
import com.root.service.CryptoCurrencyService;
import com.root.service.InvestmentService;
import com.root.serviceImpl.ClientServiceIMPL;
import com.root.serviceImpl.CryptoCurrencyServiceIMPL;
import com.root.serviceImpl.InvestmentServiceIMPL;


public class SellCrypto extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(SellCrypto.class); 
	private InvestmentService investment = new InvestmentServiceIMPL();
	private ClientService clientService = new ClientServiceIMPL();
	private CryptoCurrencyService cryptoService = new CryptoCurrencyServiceIMPL();
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		LOGGER.info("Inside SellCrypto Servlet");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session !=null)
		{
			String clientMail = (String)session.getAttribute("clientEmail");
			String cryptoName = request.getParameter("cryptoName");
			
			try 
			{
				CryptoCurrencyModel cryptoCurrency = cryptoService.viewCrypto(cryptoName);
				ClientModel client = clientService.viewClient(clientMail);
				
				int totalInvestment = investment.totalInvest(cryptoCurrency.getCryptoId(), client.getClientId());
				int totalCoins = totalInvestment/cryptoCurrency.getCryptoPrice();
				if(totalCoins > 0)
				{
					
					session.setAttribute("coins", totalCoins);
					session.setAttribute("cryptoName", cryptoCurrency.getCryptoName());
					out.println("<html><head><link rel='stylesheet' href='home.css'></head><center><div><center>"
							+ "<h2>You have "+totalCoins+"  "+cryptoCurrency.getCryptoName()+"</h2>"
							+ "</div></center></html>");
					RequestDispatcher dispatcher = request.getRequestDispatcher("Sell.html");
					dispatcher.include(request, response);
				}
				else
				{
					out.println("<html><head><link rel='stylesheet' href='home.css'></head><center><div><center>"
							+ "<h2>You haven't invested in "+cryptoCurrency.getCryptoName()+"</h2>"
							+ "<h2><a href=\"ClientDashBoard.html\">Go back to DashBoard</a></h2>"
							+ "<h2><a href=\"Logout.html\">Logout</a></h2>"
							+ "</div></center></html>");
				}
				
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				LOGGER.info("Something went wrong from SellCrypto Servlet");
				e.printStackTrace();
			}
			
			LOGGER.info("SellCrypto Servlet Ends");
		}
		
}
	
}
