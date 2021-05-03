package com.pfe.Services;

import org.springframework.http.ResponseEntity;

import com.pfe.Entity.Device;
import com.pfe.Entity.SubUser;
import com.pfe.exception.ResourceNotFoundException;

public interface InterfaceSubUser {

	public ResponseEntity<?> updateProfile (Long cin,SubUser su) throws ResourceNotFoundException;

	
}
