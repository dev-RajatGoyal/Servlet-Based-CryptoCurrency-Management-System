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
import com.root.service.ClientService;
import com.root.serviceImpl.ClientServiceIMPL;

@WebServlet("/DeleteClient")
public class DeleteClient extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(DeleteClient.class); 
	
	private ClientService clientService = new ClientServiceIMPL();
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside DeleteClient Servlet");
		PrintWriter out = response.getWriter();
		List<ClientModel> listOfClients = new ArrayList<ClientModel>();
		
		String clientMail = request.getParameter("clientEmail");
		response.setContentType("text/html");
		
		try 
		{
			int flag = 0;
			listOfClients = clientService.readClient();
			for(ClientModel client : listOfClients)
			{
				if(clientMail.equals(client.getClientEmail()))
				{
					flag = 1;
					clientService.deleteClient(clientMail);
					LOGGER.info(client.getClientName()+" deleted successfully");
					out.println("<html><head><link rel='stylesheet' href='home.css'></head><center><div>"
							+ "<h1>"+client.getClientName()+" deleted successfully</h1>"
							+ "<h2><a href=\"AdminDashBoard.html\">Go back to DashBoard</a></h2>"
							+ "<h2><a href=\"Logout.html\">Logout</a></h2>"
							+ "</div></center>");
				}
			}
			
			if(flag==0)
			{
				LOGGER.info(clientMail+" deletion failed");
				out.println("<html><head><link rel='stylesheet' href='home.css'></head><center><div>"
						+ "<h1>Client with Email "+clientMail+" does not exist !!!</h1>"
						+ "<h2><a href=\"ClientRegistration.html\">Register New Client</a></h2>"
						+ "<h2><a href=\"AdminDashBoard.html\">Go back to DashBoard</a></h2>"
						+ "<h2><a href=\"Logout.html\">Logout</a></h2>"
						+ "</div></center>");
			}
		} 
		catch (ClassNotFoundException | SQLException e1) {
			LOGGER.info("Something went wrong from DeleteClient Servlet");
			e1.printStackTrace();
		}
		
		LOGGER.info("DeleteClient Servlet Ends");
	}

}
