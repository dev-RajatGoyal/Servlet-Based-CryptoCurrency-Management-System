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

import org.apache.log4j.Logger;

import com.root.model.AdminModel;
import com.root.service.AdminService;
import com.root.serviceImpl.AdminServiceIMPL;

public class AdminLogin extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(AdminLogin.class); 
	private AdminService adminService = new AdminServiceIMPL();
	  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside Admin Login Servlet");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//creating the list of admin
		List<AdminModel> listOfAdmins = new ArrayList<AdminModel>();
		
		//fetching the details from the AdminLogin.html form 
		String adminMail = request.getParameter("adminEmail");
		String adminPass = request.getParameter("adminPassword");
		
		
		try 
		{
			int flag = 0;
			
			//getting the list of admins from the database
			listOfAdmins = adminService.readAdmin(adminMail, adminPass);
			
			for(AdminModel admin : listOfAdmins)
			{
				//validating the admin credentials 
				if(adminMail.equals(admin.getAdminEmail()) && adminPass.equals(admin.getAdminPassword()))
				{	
					LOGGER.info("Admin login successfully");
					flag = 1;
					out.println("<center><h1>Welcome "+admin.getAdminName()+"</h1></center>");
					RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashBoard.html");
					dispatcher.include(request, response);
				}
			}
			
			if(flag==0)
			{
				LOGGER.info("Admin login failed");
				response.sendRedirect("AdminLoginError.html");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			LOGGER.info("Something went wrong from Admin Login Servlet");
			e.printStackTrace();
		}
		
		LOGGER.info("AdminLogin Servlet ends");
	}

}
