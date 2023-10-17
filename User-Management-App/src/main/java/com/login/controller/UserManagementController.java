package com.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.login.Service.UserManagementService;
import com.login.entity.UserManagement;



@Controller
public class UserManagementController {

	@Autowired
	private UserManagementService service;
	
	
	@GetMapping("/login-page")
	public String saveOrUpdate(Model model) {
		model.addAttribute("user", new UserManagement());
		return "register";
	}
	@PostMapping("/Saved-user")
	public String saveUser(@ModelAttribute ("user") UserManagement management ,
			@RequestParam("email") String email ,String fName,Model model) {
		String email2 = service.getEmail(email);
		
		
		if(email.equals(email2)) {
			model.addAttribute("cmsg", "Email is already registered...!");
			 model.addAttribute("user", new UserManagement());
		}
		else {
			
			management.setPassword(service.generateCommonTextPassword());
			 service.saveOrUpdate(management);
			 model.addAttribute("msg","Registraition success");
			 String unlockAcc = service.unlockAcc(management);
			 System.out.println(unlockAcc);
			 model.addAttribute("user", new UserManagement());
		}
		
		 
			 
		return "register";
	}
	
	@GetMapping("/")
	public String login(Model model)
	{
		model.addAttribute("user", new UserManagement());
		return "index";
	}
	@PostMapping("/login")
	public String existingUser(@RequestParam("email") String email ,
			@RequestParam("password") String password ,Model model)
	{
		
		model.addAttribute("user", new UserManagement());
		String email2 = service.getEmail(email);
		String loginPassword = service.loginPassword(password);
		
			if(email.equals(email2)) {
				
				if(password.equals(loginPassword)) {
					
					System.out.println(email+" Credentials success");
					model.addAttribute("msg","Credentials success");
					return "welcome";
					
				}
				else {
					
					System.out.println(email+"Password incorrect");
					model.addAttribute("msgp","Password incorrect");
					return "index";
					
				}
				
			}
		
		else {
			System.out.println(email+" This is not a registerd email ");
			model.addAttribute("msgp","This is not a registerd email");
			return "index";
		}


	}
	@GetMapping("/unlock-page")
	public String unlockAcc(@RequestParam("id") Integer id ,Model model) {
		UserManagement userId = service.getUserId(id);
		model.addAttribute("user", userId);
		return "unlock";
	}
	@PostMapping("/unlock")
	public String unlockAcc(@RequestParam("id") Integer id 
			,@RequestParam("password") String password,
			@RequestParam("npassword") String npassword,
			@RequestParam("tpassword") String tpassword,
			@RequestParam("spassword") String spassword,
			 String email ,Model model) {
		
		UserManagement userId = service.getUserId(id);
		model.addAttribute("user", userId);
		
		
		String dPassword = service.loginPassword(password);
		String email2 = service.getEmail(email);
		
		if(tpassword.equals(dPassword)) {
			
			
			if(npassword.equals(spassword)) {
				
				service.updatePassword(email2, npassword);
				model.addAttribute("smsg", "password changed...");
				System.out.println("password changed..."+email2+" "+password);
			}
			else {
				model.addAttribute("cmsg", "Password Not Matched...!");
				System.out.println(password+" "+tpassword+" "+spassword+" "+npassword);
				
			}
	
		}
		else {
			model.addAttribute("cmsg", "Temporary Password Incorrect...!");
			System.out.println("temporary Password incorrect...!"+"  "+email2+" "+password+" "+tpassword);
		}
		
		
		
	
		return "unlock";
	}
	
	
}
