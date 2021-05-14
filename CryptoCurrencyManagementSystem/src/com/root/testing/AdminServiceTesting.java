package com.root.testing;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.root.model.AdminModel;
import com.root.service.AdminService;
import com.root.serviceImpl.AdminServiceIMPL;

public class AdminServiceTesting {

	private AdminService adminService;
	 
	@Test
	public void ReadAdminSuccessful() throws ClassNotFoundException, SQLException
	{
		adminService = new AdminServiceIMPL();
		ArrayList<AdminModel> list = (ArrayList<AdminModel>) adminService.readAdmin("admin@gmail.com", "admin@123");
		AdminModel adminValidate = new AdminModel();
		adminValidate.setAdminEmail("admin@gmail.com");
		String email = adminValidate.getAdminEmail();
		int actual = 1;
		int expected = 1;
		for(AdminModel admin : list)
		{
			if(email.equals(admin.getAdminEmail()))
			{
				actual = 1;
			}
		}	
		assertEquals(expected,actual);
	}
	
}
