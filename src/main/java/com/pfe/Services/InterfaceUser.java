package com.pfe.Services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;


import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.SubUser;
import com.pfe.exception.ResourceNotFoundException;

public interface InterfaceUser {

	/* SubUser Management */
	public ResponseEntity<?> addSubUser(Long cinu,SignupRequest signUpRequest) throws ResourceNotFoundException ;
	public Map<String, Boolean> deleteSubUser(Long cin) throws ResourceNotFoundException; 


}
