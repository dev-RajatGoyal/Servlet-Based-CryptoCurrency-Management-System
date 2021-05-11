package com.root.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.root.model.ClientModel;
import com.root.model.CryptoCurrencyModel;
import com.root.service.CryptoCurrencyService;
import com.root.serviceImpl.CryptoCurrencyServiceIMPL;

public class DeleteCryptoCurrency extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(DeleteCryptoCurrency.class); 
	
	private CryptoCurrencyService cryptoService = new CryptoCurrencyServiceIMPL();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside Delete CryptoCurrency Servlet");
		PrintWriter out = response.getWriter();
		List<CryptoCurrencyModel> listOfCryptos = new ArrayList<CryptoCurrencyModel>();
		String cryptoNAME = request.getParameter("cryptoName");
		response.setContentType("text/html");
		
		try 
		{
			int flag = 0;
			listOfCryptos = cryptoService.readCryptoCurrency();
			for(CryptoCurrencyModel crypto : listOfCryptos)
			{
				if(cryptoNAME.equals(crypto.getCryptoName()))
				{
					LOGGER.info(crypto.getCryptoName()+" deleted successfully");
					flag = 1;
					cryptoService.deleteCryptoCurrency(cryptoNAME);
					out.println("<html><head><link rel='stylesheet' href='home.css'></head><center><div>"
							+ "<h1>"+crypto.getCryptoName()+" deleted successfully</h1>"
							+ "<h2><a href=\"AdminDashBoard.html\">Go back to DashBoard</a></h2>"
							+ "<h2><a href=\"Logout.html\">Logout</a></h2>"
							+ "</div></center>");
				}
			}
			
			if(flag==0)
			{
				LOGGER.info(cryptoNAME+" deletion failed");
				out.println("<html><head><link rel='stylesheet' href='home.css'></head><center><div>"
						+ "<h1>CryptoCurrency with Name "+cryptoNAME+" does not exist !!!</h1>"
						+ "<h2><a href=\"AddCryptoCurrency.html\">Add New CryptoCurrency</a></h2>"
						+ "<h2><a href=\"AdminDashBoard.html\">Go back to DashBoard</a></h2>"
						+ "<h2><a href=\"Logout.html\">Logout</a></h3>"
						+ "</div></center>");
			}
		} 
		catch (ClassNotFoundException | SQLException e1) {
			LOGGER.info("Something went wrong from DeleteCryptoCurrency Servlet");
			e1.printStackTrace();
		}
		
		LOGGER.info("DeleteCryptoCurrency Servlet Ends");
	}

}
