package com.pfe.Controllers;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pfe.Security.JWT.JwtUtils;
import com.pfe.Repository.RoleRepository;
import com.pfe.Repository.AdministratorRepository;
import com.pfe.Repository.SubUserRepository;
import com.pfe.Entity.Administrator;
import com.pfe.Entity.ERole;
import com.pfe.Entity.MessageResponse;
import com.pfe.Entity.Role;
import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.SubUser;
import com.pfe.Request.LoginRequest;
import com.pfe.Response.JwtResponse;
import com.pfe.Security.Services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RoleRepository roleRepository;	
	
	@Autowired
	SubUserRepository userRepository;	
	
	@Autowired
	AdministratorRepository adminRepository;	
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	

	//******* USER LOGIN ******//
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getCin(), 
												 userDetails.getUsername(), 
												 userDetails.getName(),
												 userDetails.getSurname(),
												 userDetails.getTel(),
												 userDetails.getDateBirth(),
												 userDetails.getEmail(), 
												
												 roles,
												 userDetails.getImageurl()));
								
	}

	//******* USER REGISTRATION ******//
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByCin(signUpRequest.getCin())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Cin is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		if (userRepository.existsByTel(signUpRequest.getTel())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Telephone Number is already in use!"));
		}
	
		// Create new user's account
		SubUser user = new SubUser(  signUpRequest.getCin(),
									 signUpRequest.getUsername(),
							 		 encoder.encode(signUpRequest.getPassword()),
									 signUpRequest.getEmail(),
									 signUpRequest.getDateBirth(),
									 signUpRequest.getName(),
									 signUpRequest.getSurname(),
									 signUpRequest.getTel(),
									 "../../../assets/users.png"
									 
							
						
							 )  ;

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

		
			
					user.setRoles(roles);
					userRepository.save(user);	
					adminRepository.save(new Administrator(user.getCin(),user));
					
					break;
				
					
				default:
					Role suserRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(suserRole);
					user.setRoles(roles);
					userRepository.save(user);
				}
			});
		}


	

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}