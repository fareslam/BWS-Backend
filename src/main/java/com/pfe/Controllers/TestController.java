package com.pfe.Controllers;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Email.MailResponse;
import com.pfe.Entity.Device;
import com.pfe.Entity.MessageResponse;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;
import com.pfe.Repository.DeviceRepository;
import com.pfe.Repository.SubUserRepository;
import com.pfe.Repository.UserRepository;
import com.pfe.Services.InterfaceSubUser;
import com.pfe.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/test")
@CrossOrigin
public class TestController {

	
	@Autowired
	UserRepository ur;
	
	@Autowired
	SubUserRepository sur;
	
	
	@Autowired
	DeviceRepository dr;
	@Autowired
	InterfaceSubUser isu;
	
	@GetMapping("/device/{cinu}/{reference}")	
	public List<Device> listDevices(@PathVariable Long cinu,@PathVariable String reference) throws ResourceNotFoundException,Exception{
		User u = ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown user with cin: " + cinu));
		
		List <Device> lista = this.ur.listDevicesPerUser(cinu);
		
		Device d = dr.findByReference(reference) ;
		//= dr.findByReference(reference)
		for(int i =0;i<lista.size();i++) {
			
			if(!(lista.contains(d))) {
				throw new Exception("This device is not actually in list !");
			}
	}
		return lista;
}

	
	
	

	
	   @GetMapping("/resetpassword/{email}")
	    public MailResponse resetPassword(@PathVariable("email") String email) throws MessagingException,Exception {
		return   isu.resetPassword(email);
	
	      
	    }


	
	   
	
	@PutMapping("/{cin}/updateProfile")
	public ResponseEntity<?> updateProfile(@PathVariable(value = "cin") Long cin,@Valid @RequestBody SubUser su) 
			throws ResourceNotFoundException {
		return this.isu.updateProfile(cin, su);
	}
}
