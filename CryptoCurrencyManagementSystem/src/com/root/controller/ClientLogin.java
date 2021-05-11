package com.root.controller;

import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.root.model.ClientModel;
import com.root.service.ClientService;
import com.root.serviceImpl.ClientServiceIMPL;

public class ClientLogin extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(ClientLogin.class); 
	 
	private ClientService clientService = new ClientServiceIMPL();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside ClientLogin Servlet");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//creating the list of clients
		List<ClientModel> listOfClients = new ArrayList<ClientModel>();
		
		//fetching the details from the ClientLogin.html form 
		String clientMail = request.getParameter("Email");
		String clientPass = request.getParameter("Password");
		
		try 
		{
			int flag = 0;
			
			//fetching the list of clients from the database
			listOfClients = clientService.readClient();
			
			for(ClientModel client : listOfClients)
			{
				//validating client credentials
				if(clientMail.equals(client.getClientEmail()) && clientPass.equals(client.getClientPassword()))
				{	
					LOGGER.info(client.getClientName()+" login successfully with "+client.getClientEmail());
					HttpSession session = request.getSession();
					session.setAttribute("clientEmail", client.getClientEmail());
					flag = 1;
					out.println("<center><h1>Welcome "+client.getClientName()+"</h1></center>");
					RequestDispatcher dispatcher = request.getRequestDispatcher("ClientDashBoard.html");
					dispatcher.include(request, response);
				}
			}
			 
			if(flag==0)
			{
				LOGGER.info(clientMail+" failed to login due to invelid credentials or client might not registered");
				response.sendRedirect("ClientLoginError.html");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.info("Something went wrong from Client Login Servlet");
			e.printStackTrace();
		}
	}
}
