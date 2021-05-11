package com.root.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.root.model.ClientModel;
import com.root.service.ClientService;
import com.root.serviceImpl.ClientServiceIMPL;

public class ClientShowBalance extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(ClientShowBalance.class); 
	
	private ClientService clientService = new ClientServiceIMPL();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside ClientShowBalance Servlet");
		//String clientMail =  request.getParameter("clientEmail");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
  
		HttpSession session = request.getSession(false);
		if(session !=null)
		{
			//getting the login client email
			String clientMail = (String)session.getAttribute("clientEmail");
			try  
			{
				//fetching the client details to show client balance 
				ClientModel client = clientService.showBalance(clientMail);
				
				//getting client's total investment
				int totalInvestment = clientService.totalInvestment(client.getClientId());
				
					String name = client.getClientName();
					int balance = client.getClientBalance();
					int invest = client.getClientInvestment();
					LOGGER.info(name+" showing balance");
					out.println("<link rel=\"stylesheet\" href=\"home.css\"/>"
							+ "<center>"
							+ "<div>"
							+ "<h2>Hello "+name+"</h2>"
							+ "<h2>Your current balance is : "+balance+"</h2>"
							+ "<h2>Your total investment is : "+invest+"</h2>"
							+ "<h3><a href=\"ClientDashBoard.html\">Back to DashBoard</a></h3>"
							+ "<h3><a href=\"Logout.html\">Log out</a></h3></center>"
							+ "</div>");  
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				LOGGER.info("Something went wrong from ClientShowBalance");
				e.printStackTrace();
			}
			
			LOGGER.info("ClientShowBalance Servlet Ends");
	//	}
		
		}
	}

}
