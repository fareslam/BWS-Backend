package com.pfe.Services;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.pfe.Email.EmailService;
import com.pfe.Email.MailResponse;
import com.pfe.Entity.MessageResponse;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;
import com.pfe.Repository.SubUserRepository;
import com.pfe.Repository.UserRepository;
import com.pfe.exception.ResourceNotFoundException;



@Service
@Transactional
public class SubUserService implements InterfaceSubUser{

	@Autowired
	SubUserRepository sur;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	@Autowired
	  EmailService emailService;
	 
	  private Logger LOGGER = LoggerFactory.getLogger(getClass());
	

	  
	   @Override
	    public MailResponse resetPassword(@PathVariable(value = "email") String email) throws MessagingException, Exception {
			SubUser subuser = sur.findByEmail(email)
					.orElseThrow(() -> new ResourceNotFoundException("Unkown user with email : " + email));
	        if (subuser == null) {
	            throw new Exception("No user found by email :" + email);
	        }
	        String password = generatePassword();
	        subuser.setPassword(encoder.encode(password));
	        sur.save(subuser);
	        LOGGER.info("New user password: " + password);
	    	Map<String, Object> model = new HashMap<>();
	    	model.put("name", subuser.getName());
	    	model.put("surname", subuser.getSurname());
			model.put("Email", email);
			model.put("password", password);
	        
		return	emailService.sendEmail(email, model);
	    
	    }

	  
	
	
	@Override
	public ResponseEntity<?> updateProfile(@PathVariable(value = "cin") Long cin,@Valid @RequestBody SubUser su) throws ResourceNotFoundException {

		String ch=su.getUsername();
		String p=su.getPassword();
		Long t=su.getTel();

		SubUser subuser = sur.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown user with cin : " + cin));
		
		if (sur.existsByUsername(su.getUsername()) && (ch !=su.getUsername()) ) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		
		
	
	
		subuser.setDateBirth(su.getDateBirth());
		subuser.setUsername(su.getUsername());
		subuser.setPassword(encoder.encode(su.getPassword() ) );
		subuser.setImageurl(su.getImageurl());
		subuser.setSurname(su.getSurname());
		subuser.setName(su.getName());
		
	

		if ((ch.length()==0)||(ch==null)) 	return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error in Username !"));
		
		
		if ((p.length()==0)||(p==null)) 	return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error in Password !"));
		return new ResponseEntity<>(this.sur.save(subuser), HttpStatus.CREATED);
	}
	
	
	
	


	private String generatePassword() {
		// TODO Auto-generated method stub
		 return RandomStringUtils.randomNumeric(10);
	}

	
}
