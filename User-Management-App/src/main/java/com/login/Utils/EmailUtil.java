package com.login.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.login.Service.UserManagementService;
import com.login.entity.UserManagement;
import com.login.repository.UserManagementRepo;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String sendmail(UserManagement management) {
		
		boolean status= false;
		
		Integer id = management.getId();
		String email = management.getEmail();
		String password = management.getPassword();
		String getfName = management.getfName();
		String getlName = management.getlName();
		
		String str="<p>Hi "+getfName+" "+getlName+"<br><br>Welcome to IES family, your registration is almost complete.<br><br>\r\n"
				+ "Please unlock your account using below details.<br><br>\r\n"
				+ "Temporary Password  : "+password+"<br><br>\r"
				+"<a href=http://localhost:8080/unlock-page?&id="+id+">Link to unlock account</a><br><br>\r"
				+"Thanks<br><br>\rIES Team</p>";
		try {
			
			MimeMessage  message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setTo(email);
			helper.setSubject("Unlock IES Account");
			helper.setText(str,true);
			
			javaMailSender.send(message);
			
			return "Mail Sent Successfully...";
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error while Sending Mail";
	}

}
