package com.root.controller;

import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.root.model.CryptoCurrencyModel;
import com.root.service.CryptoCurrencyService;
import com.root.serviceImpl.CryptoCurrencyServiceIMPL;

public class AddCryptoCurrency extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(AddCryptoCurrency.class); 
	 
	private CryptoCurrencyService cryptoService = new CryptoCurrencyServiceIMPL();
	  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside Add CryptoCurrency Servlet to add CryptoCurrency in Database");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//fetching cryptocurrency details from the (AddCryptoCurrency.html) form
		int cryptoID = Integer.parseInt(request.getParameter("cryptoId"));
		String cryptoNAME = request.getParameter("cryptoName");
		int cryptoPRICE = Integer.parseInt(request.getParameter("cryptoPrice"));
		
		//creating cryptocurrency object to store in database
		CryptoCurrencyModel cryptoModel = new CryptoCurrencyModel(cryptoID, cryptoNAME, cryptoPRICE);
		
		out.println("<center><h2>"+cryptoModel.getCryptoName()+", Added successfully</h2>"
				+ "<h3><a href=\"AdminDashBoard.html\">Go back to DashBoard</a</h3>"
				+ "</center>");
		
		try 
		{
			LOGGER.info(cryptoNAME+" CryptoCurreny added successfully");
			cryptoService.addCryptoCurrency(cryptoModel);
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			LOGGER.info("Something went wrong, "+cryptoNAME+" CryptoCurreny failed to add");
			e.printStackTrace();
		}
		
		LOGGER.info("Add CryptoCurrency Ends");
	}

}
