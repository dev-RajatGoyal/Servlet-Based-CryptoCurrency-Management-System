package com.root.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.root.model.CryptoCurrencyModel;
import com.root.service.CryptoCurrencyService;
import com.root.serviceImpl.CryptoCurrencyServiceIMPL;

public class InvestSellCrypto extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(InvestSellCrypto.class); 
	private CryptoCurrencyService cryptoService = new CryptoCurrencyServiceIMPL();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside InvestSellCrypto Servlet");
		
		List<CryptoCurrencyModel> listOfCryptos = new ArrayList<CryptoCurrencyModel>();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 
		try 
		{
			listOfCryptos = cryptoService.readCryptoCurrency();
			out.println("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<link rel=\"stylesheet\" href=\"table.css\"/>"
					+ "<title>Details of All CryptoCurrency</title>\r\n"
					+ "</head>\r\n"
					+ "<body><center>");
			out.println("<h2>All CryptoCurrency details</h2>");
			out.println("<table border=2 class='content-table'>");
			out.println("<thead><tr>"
					+ "<th>CryptoCurrency Id</th>"
					+ "<th>CryptoCurrency Name</th>"
					+ "<th>CryptoCurrency Price</th>"
					+ "<th>Invest</th>"
					+ "<th>Sell</th>"
					+ "</tr></thead>");
			for(CryptoCurrencyModel crypto :listOfCryptos)
			{
				out.println("<tbody><tr>"
						+ "<td>"+crypto.getCryptoId()+"</td>"
						+ "<td>"+crypto.getCryptoName()+"</td>"
						+ "<td>"+crypto.getCryptoPrice()+"</td>"
						+ "<td><h3><a href=\"Investment?cryptoName="+crypto.getCryptoName()+"\">Invest</a></h3></td>"
						+ "<td><h3><a href=\"SellCrypto?cryptoName="+crypto.getCryptoName()+"\">Sell</a></h3></td>"
						+ "</tr></tbody>");
			}
			out.println("</table>");
			out.println("<a href=\"ClientDashBoard.html\"><h2>Back to DashBoard</h2></a>");
			out.println("</center></body>\r\n"
					+ "</html>");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			LOGGER.info("Something went wrong from InvestSellCrypto Servlet");
			e.printStackTrace();
		}
		
		LOGGER.info("InvestSellCrypto Servlet ends");
	}

	
}
