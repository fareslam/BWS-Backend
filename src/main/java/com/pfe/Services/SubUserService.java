package com.pfe.Services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
	
	@Override
	public ResponseEntity<?> updateProfile(@PathVariable(value = "cin") Long cin,@Valid @RequestBody SubUser su) throws ResourceNotFoundException {

		String ch=su.getUsername();
		String p=su.getPassword();

		SubUser subuser = sur.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown user with cin : " + cin));
		
		if (sur.existsByUsername(su.getUsername())) {
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

	
}
