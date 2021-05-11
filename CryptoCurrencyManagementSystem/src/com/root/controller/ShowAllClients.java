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

import org.apache.log4j.Logger;

import com.root.model.ClientModel;
import com.root.service.ClientService;
import com.root.serviceImpl.ClientServiceIMPL;

public class ShowAllClients extends HttpServlet {   
	static final Logger LOGGER = Logger.getLogger(ShowAllClients.class); 
	private ClientService clientService = new ClientServiceIMPL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("Inside ShowAllClient Servlet");
		
		List<ClientModel> listOfClients = new ArrayList<ClientModel>();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		try 
		{
			listOfClients = clientService.readClient();
			out.println("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>Details of All Clients</title>\r\n"
					+ "<link rel=\"stylesheet\" href=\"table.css\"/>"
					+ "</head>\r\n"
					+ "<body><center>");
			out.println("<h2>All clients details</h2>");
			out.println("<table border=2 class='content-table'>");
			out.println("<thead><tr>"
					+ "<th>Client Id</th>"
					+ "<th>Client Name</th>"
					+ "<th>Client Email</th>"
					+ "<th>Client Phone</th>"
					+ "<th>Client Address</th>"
					+ "<th>Client Balance</th>"
					+ "<th>Client Investment</th>"
					+ "</tr></thead>");
			for(ClientModel client :listOfClients)
			{
				out.println("<tbody><tr>"
						+ "<td>"+client.getClientId()+"</td>"
						+ "<td>"+client.getClientName()+"</td>"
						+ "<td>"+client.getClientEmail()+"</td>"
						+ "<td>"+client.getClientPhone()+"</td>"
						+ "<td>"+client.getClientAddress()+"</td>"
						+ "<td>"+client.getClientBalance()+"</td>"
						+ "<td>"+client.getClientInvestment()+"</td>"
						+ "</tr></tbody>");
			}
			out.println("</table>");
			out.println("<a href=\"AdminDashBoard.html\"><h2>Back to DashBoard</h2></a>");
			out.println("</center></body>\r\n"
					+ "</html>");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			LOGGER.info("Something went wrong from ShowAllClient Servlet");
			e.printStackTrace();
		}
		
		LOGGER.info("ShowAllClients Servlet Ends");
	}

}
