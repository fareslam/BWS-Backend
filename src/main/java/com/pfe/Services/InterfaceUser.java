package com.pfe.Services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;


import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.ClientArea.ClientArea;
import com.pfe.Entity.SubUserSpace.SubUser_Space;
import com.pfe.exception.ResourceNotFoundException;

public interface InterfaceUser {

	/* SubUser Management */
	public ResponseEntity<?> addSubUser(Long cinu,SignupRequest signUpRequest) throws ResourceNotFoundException ;
	public ResponseEntity<?> UpdateSubUser(Long cinu,Long cin,SignupRequest signUpRequest) throws ResourceNotFoundException ;
	public Map<String, Boolean> deleteSubUser(Long cin) throws ResourceNotFoundException; 

	 /*Employee Space Management*/
	  public SubUser_Space addSubUser_Space(SubUser_Space sus);
	  public  List<SubUser_Space> listSubUserspaces();
	  public Map<String, Boolean>  deleteSubUserSpace( Long cinu,Long cin,Long idSpace) throws ResourceNotFoundException,Exception ;

}
