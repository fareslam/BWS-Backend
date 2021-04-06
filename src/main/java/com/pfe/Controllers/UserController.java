package com.pfe.Controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Entity.MessageResponse;
import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;
import com.pfe.Repository.SubUserRepository;
import com.pfe.Repository.UserRepository;
import com.pfe.Services.InterfaceUser;
import com.pfe.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/api/bws/user")
@CrossOrigin
public class UserController {


	@Autowired
	SubUserRepository sur;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	InterfaceUser iu;
	
	
	/* ********** Sub User Management *********/
	@PostMapping("/{cinu}/SubUser/add")
	public ResponseEntity<?> addSubUser(@PathVariable(value = "cinu") Long cinu,@Valid @RequestBody SignupRequest signUpRequest) throws ResourceNotFoundException {
		return this.iu.addSubUser(cinu,signUpRequest);
	}
	
	
	@DeleteMapping("/SubUser/delete/{cin}")
	public Map<String, Boolean> deleteSubUser(@PathVariable(value = "cin") Long cin) throws ResourceNotFoundException {
	
		return this.iu.deleteSubUser(cin);
	}
	
	
	@GetMapping("/{cinu}/SubUser/all")
	public List<SubUser> listSubUser(@PathVariable(value = "cinu") Long cinu) throws ResourceNotFoundException {
		User u = ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		
		return this.ur.listSubUserByUser(cinu);
	}
	
	
	@GetMapping("/{cin}")
	public Optional<User> UserBySubUser(@PathVariable(value = "cin") Long cin) throws ResourceNotFoundException {
				return this.ur.userBySubUser(cin);
	}
	
}