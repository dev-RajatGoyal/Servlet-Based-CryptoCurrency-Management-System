package com.root.controller;

import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.root.model.ClientModel;
import com.root.service.ClientService;
import com.root.serviceImpl.ClientServiceIMPL;

public class ClientRegister extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(ClientRegister.class); 
	private ClientService clientService = new ClientServiceIMPL();
	 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside ClientRegister Servlet");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try 
		{
			//creating clientModel object
			ClientModel clientModel = new ClientModel();
			
			//fetching details of client from ClientRegistraing.html form
			int clientId = Integer.parseInt(request.getParameter("cid"));
			String clientName = request.getParameter("cname");
			String clientEmail = request.getParameter("cemail");
			String clientPassword = request.getParameter("cpassword");
			String clientPhone = request.getParameter("cphone");
			String clientAddress = request.getParameter("caddress");
			int clientBalance = Integer.parseInt(request.getParameter("cbalance")); 
			
			//setting details of client to register
			clientModel.setClientId(clientId);
			clientModel.setClientName(clientName);
			clientModel.setClientEmail(clientEmail);
			clientModel.setClientPassword(clientPassword);
			clientModel.setClientPhone(clientPhone);
			clientModel.setClientAddress(clientAddress);
			clientModel.setClientBalance(clientBalance);
			
			out.println("<link rel=\"stylesheet\" href=\"home.css\"/><center><div><h2> Welcome "+clientName+", You have successfully registered</h2>"
					+ "<h3><a href=\"Home.html\">Back to Home Page</a></h3>"
					+ "</div></center>");
			
			try {
				LOGGER.info(clientName+" Registered successfully with "+clientEmail);
				clientService.insertClient(clientModel);
			} catch (ClassNotFoundException | SQLException e) {
				LOGGER.info("Client failed to register");
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			LOGGER.info("Something went wrong from ClientRegister Servlet");
			out.println("<html><head><link rel='stylesheet' href='home.css'></head><center><div><h2>Please fill all the details carefully !!!</h2>");
			out.println("<h2><a href=\"Home.html\">Back to Home Page</a</h2></div></center>");
		}
		
		LOGGER.info("ClientRegister Servlet Ends");
	} 

}
