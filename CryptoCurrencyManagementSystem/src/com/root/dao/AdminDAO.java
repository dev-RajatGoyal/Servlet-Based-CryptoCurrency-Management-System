package com.root.dao;

import java.sql.SQLException;
import java.util.List;

import com.root.model.AdminModel;

public interface AdminDAO {
	
	List<AdminModel> readAdmin(String adminEmail, String adminPassword) throws ClassNotFoundException,SQLException;
}
