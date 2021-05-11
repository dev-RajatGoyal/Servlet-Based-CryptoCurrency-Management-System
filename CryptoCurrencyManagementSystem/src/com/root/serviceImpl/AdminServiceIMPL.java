package com.root.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.root.dao.AdminDAO;
import com.root.daoImpl.AdminDaoIMPL;
import com.root.daoImpl.ClientDaoIMPL;
import com.root.model.AdminModel;
import com.root.service.AdminService;

public class AdminServiceIMPL implements AdminService{
	static final Logger LOGGER = Logger.getLogger(AdminServiceIMPL.class); 
	private AdminDAO adminDao = new AdminDaoIMPL();
	@Override
	public List<AdminModel> readAdmin(String adminEmail, String adminPassword)
			throws ClassNotFoundException, SQLException {
		LOGGER.info("Inside readAdmin from AdminServiceIMPL");
		return adminDao.readAdmin(adminEmail, adminPassword);
	}
	
	
}
