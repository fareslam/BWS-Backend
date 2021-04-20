package com.pfe.Services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Entity.ERole;

import com.pfe.Entity.Device;
import com.pfe.Entity.MessageResponse;
import com.pfe.Entity.Role;
import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;
import com.pfe.Entity.SubUserSpace.SubUser_Space;
import com.pfe.Repository.RoleRepository;
import com.pfe.Repository.SubUserRepository;
import com.pfe.Repository.SubUserSpaceRepository;
import com.pfe.Repository.UserRepository;
import com.pfe.exception.ResourceNotFoundException;


@Service
@Transactional
public class UserService implements InterfaceUser {


	@Autowired
	SubUserRepository sur;
	
	@Autowired
	SubUserSpaceRepository susr;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	RoleRepository roleRepository;
	
	/*Add sub user*/
	@Override
	public ResponseEntity<?> addSubUser(@PathVariable(value = "cinu") Long cinu,@Valid @RequestBody SignupRequest signUpRequest)throws ResourceNotFoundException  {
		
		User u = ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		
		if (sur.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (sur.existsByCin(signUpRequest.getCin())) 
		{
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Cin is already taken!"));
		}

		if (sur.existsByEmail(signUpRequest.getEmail())) 
		{
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		if (sur.existsByTel(signUpRequest.getTel())) 
		{
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Telephone Number is already in use!"));
		}
	
		// Create new user's account
		SubUser user = new SubUser(signUpRequest.getCin(),
							 signUpRequest.getUsername(),
							 signUpRequest.getPassword(),
							 signUpRequest.getEmail(),
							 signUpRequest.getDateBirth(),
							 signUpRequest.getName(),
							 signUpRequest.getSurname(),
							 signUpRequest.getTel(),
							 u.getCinu()
							);
			

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "read":
					
					Role readRole = roleRepository.findByName(ERole.ROLE_SUREAD)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(readRole);
	
					user.setRoles(roles);
					sur.save(user);
					
					break;
					
				case "write":
					
					Role writeRole = roleRepository.findByName(ERole.ROLE_SUWRITE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(writeRole);
					user.setRoles(roles);
					sur.save(user);
			

					break;
			
					
				default:
					Role suserRole = roleRepository.findByName(ERole.ROLE_SUREAD)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(suserRole);

					
					user.setRoles(roles);
					sur.save(user);
				}
			});
		}

		return ResponseEntity.ok(new MessageResponse("SubUser registered successfully!"));
	}

public ResponseEntity<?> UpdateSubUser(@PathVariable(value = "cinu") Long cinu,
		@PathVariable(value = "cin") Long cin,@Valid @RequestBody SignupRequest signUpRequest)throws ResourceNotFoundException  {
		
		User u = ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		
		SubUser subuser = sur.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cin));
		
		
	

			

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "read":
					
					Role readRole = roleRepository.findByName(ERole.ROLE_SUREAD)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(readRole);
	
					subuser.setRoles(roles);
					sur.save(subuser);
					
					break;
					
				case "write":
					
					Role writeRole = roleRepository.findByName(ERole.ROLE_SUWRITE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(writeRole);
					subuser.setRoles(roles);
					sur.save(subuser);
			

					break;
			
					
				default:
					Role suserRole = roleRepository.findByName(ERole.ROLE_SUREAD)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(suserRole);

					
					subuser.setRoles(roles);
					sur.save(subuser);
				}
			});
		}

		return ResponseEntity.ok(new MessageResponse("Role Updated successfully!"));
	}


	@Override
	public Map<String, Boolean> deleteSubUser(@PathVariable(value = "cin") Long cin) throws ResourceNotFoundException {
		SubUser su = sur.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown device with reference : " + cin));
		
		sur.delete(su);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted Sub User !", Boolean.TRUE);
		return response;
	}


	@Override
	public SubUser_Space addSubUser_Space(SubUser_Space sus) {
		// TODO Auto-generated method stub
		return this.susr.save(sus);
	}


	@Override
	public List<SubUser_Space> listSubUserspaces() {
		// TODO Auto-generated method stub
		return this.susr.findAll();
	}

	


	

}
