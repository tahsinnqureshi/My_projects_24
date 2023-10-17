package com.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.login.entity.UserManagement;

import jakarta.transaction.Transactional;

@Repository
public interface UserManagementRepo extends JpaRepository<UserManagement, Integer> {

	 @Transactional
	  @Query("select email from UserManagement where email=:email ")
	  public String getEmail(String email );
	 @Transactional
	 @Query("select password from UserManagement where password=:password ")
	 public String getPassword(String password );
	 
	 @Modifying
	 @Transactional
	 @Query("update UserManagement set password =:npassword where email=:email")
	 public void updatePassword(String email, String npassword);
	
     @Modifying
     @Transactional
     @Query("select fName from UserManagement where fName=:fName")
     public String getFirstName(String fName);
     
     @Modifying
     @Transactional
     @Query("select lName from UserManagement where lName=:lName")
     public String getLastName(String lName);
     
//     @Modifying
//     @Transactional
//     @Query("select id from UserManagement where email=:email")
//     public Integer getid(String email);
}


//	@Query("select distinct(password) from UserManagement where email=:email")
//	public String getUniquepassword( String password ,String email);

