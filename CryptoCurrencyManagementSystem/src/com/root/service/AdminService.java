package com.root.service;

import java.sql.SQLException;
import java.util.List;

import com.root.model.AdminModel;

public interface AdminService {

	List<AdminModel> readAdmin(String adminEmail, String adminPassword) throws ClassNotFoundException,SQLException;
}
