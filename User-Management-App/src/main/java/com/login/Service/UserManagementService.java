 package com.login.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.login.entity.UserManagement;

import jakarta.servlet.http.HttpServletResponse;

public interface UserManagementService {

	public String saveOrUpdate(UserManagement management) ;
	
	public UserManagement getUserId(Integer uId);
	
	public String getEmail(String email);
	
	public String loginPassword(String password);
	
	public String generateCommonTextPassword();
	
	public void updatePassword(String email, String npassword);
	
	public String getFirstName(String fName);
	
	public String getLastName(String lName);
	
	public Integer getid(String email);
	
	public String unlockAcc(UserManagement management);
	
}
