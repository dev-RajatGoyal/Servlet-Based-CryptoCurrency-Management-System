package com.root.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

public class Investment extends HttpServlet {
 

	static final Logger LOGGER = Logger.getLogger(Investment.class); 
	private InvestmentService investment = new InvestmentServiceIMPL();
	private ClientService clientService = new ClientServiceIMPL();
	private CryptoCurrencyService cryptoService = new CryptoCurrencyServiceIMPL();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside investment servlet to invest in particular CryptoCurrency");
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
				
				if(client.getClientBalance() >= cryptoCurrency.getCryptoPrice())
				{
					int coins = (int) (client.getClientBalance() / cryptoCurrency.getCryptoPrice());
					session.setAttribute("coins", coins);
					session.setAttribute("cryptoName", cryptoCurrency.getCryptoName());
					out.println("<html><head><link rel='stylesheet' href='home.css'></head><center><div>"
							+ "<h2>You can buy "+coins+"  "+cryptoCurrency.getCryptoName()+"</h2>"
							+ "</div></center></html>");
					RequestDispatcher dispatcher = request.getRequestDispatcher("Investment.html");
					dispatcher.include(request, response);
				}
				else
				{
					out.println("<html><head><link rel='stylesheet' href='home.css'></head><center><div>"
							+ "<h2>You don't have sufficient balance</h2>"
							+ "<h3><a href=\"ClientDashBoard.html\">Go back to DashBoard</a></h3>"
							+ "</div></center>");
				}
				
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
		LOGGER.info("Investment Servlet Ends");
		
	}
}
