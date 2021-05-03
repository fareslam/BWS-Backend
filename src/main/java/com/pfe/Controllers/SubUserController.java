package com.pfe.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.SubUser;
import com.pfe.Repository.AdministratorRepository;
import com.pfe.Services.InterfaceSubUser;
import com.pfe.Services.InterfaceUser;
import com.pfe.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/bws/subuser")
@CrossOrigin
public class SubUserController {
	
	@Autowired
	InterfaceSubUser isu;

	@Autowired
	AdministratorRepository ar;
	
	@PutMapping("/{cin}/updateProfile")
	public ResponseEntity<?> updateProfile(@PathVariable(value = "cin") Long cin,@Valid @RequestBody SubUser su) 
			throws ResourceNotFoundException {
		return this.isu.updateProfile(cin, su);
	}

	
	/* *** Real Time Managment ***********/
	@GetMapping("/rt/{reference}")	
	public List<Float> listValRT(@PathVariable(value = "reference") String reference) 	
	throws ResourceNotFoundException{
		
		return this.ar.AllvaluesRT(reference);
	}
}
