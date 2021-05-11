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


public class UpdateClient extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(UpdateClient.class); 
	private ClientService clientService = new ClientServiceIMPL();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside UpdateClient servlet");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		ClientModel client = new ClientModel();
		if(session!=null)
		{
			String clientMail = (String)session.getAttribute("clientEmail");
			try {
				client = clientService.viewClient(clientMail);
			
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			String clientName = request.getParameter("cname");
			String clientPass = request.getParameter("cpassword");
			String clientPhone = request.getParameter("cphone");
			String clientAddress = request.getParameter("caddress");
			
			client.setClientName(clientName);
			client.setClientPassword(clientPass);
			client.setClientPhone(clientPhone);
			client.setClientAddress(clientAddress);
			
			try 
			{
				LOGGER.info(client.getClientName()+" details updated successfully");
				clientService.updateClient(client);
				out.println("<link rel=\"stylesheet\" href=\"home.css\"/><center><div>"
						+ "<h1>Your details successfully updated</h1>"
						+ "<h3><a href=\"ClientLogin.html\">Login</a></h3>"
						+ "<h3><a href=\"Logout.html\">Logout</a></h3>"
						+ "</div></center>");
			} 
			catch (ClassNotFoundException | SQLException e) {
				LOGGER.info("Something went wrong from UpdateClient servlet");
				e.printStackTrace();
			}
			
			LOGGER.info("UpdateClient Servlet Ends");
		}
	}

}
