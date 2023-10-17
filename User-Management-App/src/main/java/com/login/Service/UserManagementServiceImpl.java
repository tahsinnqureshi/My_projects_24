package com.login.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.Utils.EmailUtil;
import com.login.entity.UserManagement;
import com.login.repository.UserManagementRepo;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserManagementRepo repo;
	
	@Autowired
	private EmailUtil mail;
	
	@Override
	public String saveOrUpdate(UserManagement management) {
		Integer id = management.getId();
		repo.save(management);
		
		if(id==null) {
			return "User Account saved";
		}
		else {
			return "User Account updated";
		}
		

	}

	@Override
	public UserManagement getUserId(Integer uId) {
		Optional<UserManagement> findById = repo.findById(uId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	

	@Override
	public String generateCommonTextPassword() {
		String upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower="abcdefghijklmnopqrstuvwxyz";
		String num="0123456789";
		String specialChar="<>,.?/!@#$%^&*()";
		String combination=upper+lower+num+specialChar;
		int len=8;
		String s="";
		
		char [] password= new char[len];
		Random r= new Random();
		
		for(int i=0;i<len;i++) {
			
			password[i]=combination.charAt(r.nextInt(combination.length()));
		
			
		}
		
		s=password.toString();
		return s;
	}

	

	@Override
	public String getEmail(String email) {
		return repo.getEmail(email);
	}

	@Override
	public String loginPassword(String password) {
		return repo.getPassword(password);
	}

	@Override
	public void updatePassword(String email, String nPassword) {
		
		repo.updatePassword(email, nPassword);
		
	}

	@Override
	public String getFirstName(String fName) {
		return repo.getFirstName(fName);	}

	@Override
	public String getLastName(String lName) {
		return repo.getLastName(lName);
	}

	@Override
	public Integer getid(String email) {
		return null;
	}

	@Override
	public String unlockAcc(UserManagement management){
		String sendmail = mail.sendmail(management);
		return sendmail;
		
	}

	

//	@Override
//	public String loginPassword(String password) {
//		String uniquepassword = repo.getUniquepassword(password);
//		 
//		return uniquepassword;
//	}



	

}
