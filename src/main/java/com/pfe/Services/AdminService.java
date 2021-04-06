package com.pfe.Services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Repository.RoleRepository;
import com.pfe.Entity.Area;
import com.pfe.Entity.Constraint_CO2;
import com.pfe.Entity.Device;
import com.pfe.Entity.ERole;
import com.pfe.Entity.MessageResponse;
import com.pfe.Entity.Role;
import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.Space;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;
import com.pfe.Entity.ClientArea.ClientArea;
import com.pfe.Entity.UserDevices.UserDevices;
import com.pfe.Repository.AreaRepository;
import com.pfe.Repository.ClientAreaRepository;
import com.pfe.Repository.ConstraintRepository;
import com.pfe.Repository.DeviceRepository;
import com.pfe.Repository.SpaceRepository;
import com.pfe.Repository.SubUserRepository;
import com.pfe.Repository.UserDevicesRepository;
import com.pfe.Repository.UserRepository;
import com.pfe.exception.ResourceNotFoundException;

@Service
@Transactional
public class AdminService implements InterfaceAdmin {
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	SubUserRepository sur;
	
	@Autowired 
	ClientAreaRepository car;
	
	@Autowired
	DeviceRepository dr;  
	
	@Autowired 
	UserDevicesRepository udr;
	
	@Autowired
	ConstraintRepository cr;
	
	@Autowired
	AreaRepository ar;
	
	@Autowired
	SpaceRepository sr;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	/*delete device */
	@Override
	public Map<String, Boolean>  deleteDevice(@PathVariable(value = "reference") String reference) throws ResourceNotFoundException {
			Device d = dr.findByReference(reference)
					.orElseThrow(() -> new ResourceNotFoundException("Unkown device with reference : " + reference));
			
				dr.delete(d);
			
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted device !", Boolean.TRUE);
			return response;
	}

	/*adding a device */
	@Override
	public ResponseEntity<?> addDevice(@Valid @RequestBody Device d) {
		if (dr.existsByReference(d.getReference())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Device's Reference is already taken!"));
		}
		Device device=new Device();
		device.setReference(d.getReference());
		device.setLongitude(d.getLatitude());
		device.setLatitude(d.getLatitude());
		device.setName(d.getName());
		device.setIdConstraint(d.getIdConstraint());
		device.setIdSpace(d.getIdSpace());
		
		return new ResponseEntity<>(this.dr.save(device), HttpStatus.CREATED);
	}

	/*update a device */
	@Override
	public ResponseEntity<?> updateDevice(@PathVariable(value = "reference") String reference, @Valid @RequestBody Device d) 
			throws ResourceNotFoundException{

		Device device = dr.findByReference(reference)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown device with reference " +reference));
		device.setLongitude(d.getLatitude());
		device.setLatitude(d.getLatitude());
		device.setName(d.getName());
		device.setIdConstraint(d.getIdConstraint());
		device.setIdSpace(d.getIdSpace());
		return new ResponseEntity<>(this.dr.save(device), HttpStatus.CREATED);
	}

	/*view all the devices */
	@Override
	public List<Device> listDevices() {
		return dr.findAll();
	}

	/*delete a user */
	@Override
	 public Map<String, Boolean> deleteUser(@PathVariable(value = "cinu")Long cinu) throws ResourceNotFoundException {
			User u = ur.findByCinu(cinu)
					.orElseThrow(() -> new ResourceNotFoundException("Unkown user with cin : " + cinu));
			
			SubUser su = sur.findByCin(cinu)
					.orElseThrow(() -> new ResourceNotFoundException("Unkown user with cin: " + cinu));
			
				sur.delete(su);
				ur.delete(u);
		
			
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted user !", Boolean.TRUE);
			return response;
	}

	
	/*add a user */
	@Override
	public ResponseEntity<?> addUser(@RequestBody @Valid SignupRequest signUpRequest) {
		// TODO Auto-generated method stub
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
	
		// Create a new user account
		SubUser subuser = new SubUser();
					subuser.setCin(signUpRequest.getCin());	
					subuser.setUsername(signUpRequest.getUsername());		 
					subuser.setPassword(encoder.encode(signUpRequest.getPassword()));		
					subuser.setEmail( signUpRequest.getEmail());		
					subuser.setDateBirth(signUpRequest.getDateBirth());
					subuser.setName(signUpRequest.getName());	 
					subuser.setSurname(signUpRequest.getSurname()); 
					subuser.setTel(signUpRequest.getTel()); 
							
		

		Set<Role> roles = new HashSet<>();
		Role subw = roleRepository.findByName(ERole.ROLE_SUWRITE)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(subw);
			
				Role subre = roleRepository.findByName(ERole.ROLE_SUREAD)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(subre);
				
				Role useRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(useRole);
				
				subuser.setRoles(roles);
				
		
		this.sur.save(subuser);
			User user=new User();
			user.setCinu(subuser.getCin());
			user.setCin_admin(signUpRequest.getCin_admin());
			user.setSubUser(subuser);
					
					ur.save(user);
					return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	/*view all users*/
	@Override
	public List<User> listUsers() {
		return ur.findAll();
	}

	/* affect a device to a user */
	@Override
	public ResponseEntity<?> affectUserDevice(@Valid @RequestBody UserDevices ud) {
		if (!dr.existsByReference(ud.getUdk().getReference()))
		{
			return new ResponseEntity<>(new MessageResponse("Unknown device reference "+ud.getUdk().getReference()),HttpStatus.NOT_FOUND);
		}
		
		if (!ur.existsByCinu(ud.getUdk().getCinu()))
		{
			return new ResponseEntity<>(new MessageResponse("Unknown User Cin "+ud.getUdk().getCinu()),HttpStatus.NOT_FOUND);
		}
	
		 return new ResponseEntity<>(this.udr.save(ud),HttpStatus.CREATED);
	}

	/*get device by ref  */
	@Override
	public Device getDeviceByReference(@PathVariable(value = "reference") String reference) throws ResourceNotFoundException {

		return this.dr.findByReference(reference)
				.orElseThrow(() -> new ResourceNotFoundException("Unknown device with reference:" + reference));
	}

	/* get User By Cin*/
	@Override
	public User getUserByCin(@PathVariable(value = "cinu") Long cinu) throws ResourceNotFoundException {

		return this.ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unknown user with cin:" + cinu));	}

	/* add a constraint*/
	@Override
	public Constraint_CO2 addConstraint(@Valid @RequestBody Constraint_CO2 c) {
		return this.cr.save(c);
	}

	/* add an area*/
	@Override
	public Area addArea(@Valid @RequestBody Area a) {

		return this.ar.save(a);
	}

	/* add a space*/
	@Override
	public Space addSpace(@Valid @RequestBody Space s) {

		return this.sr.save(s);
	}

	/* list Constaints*/
	@Override
	public List<Constraint_CO2> listConstraints() {
		return this.cr.findAll();
	}

	/* list areas*/
	@Override
	public List<Area> listAreas() {
		return this.ar.findAll();
	}

	/* list spaces*/
	@Override
	public List<Space> listSpaces() {
		return this.sr.findAll();
	}

	@Override
	public List<UserDevices> ListUserDevices() {
		return this.udr.findAll();	}

	@Override
	public ClientArea addClientArea(ClientArea ca) {
		return this.car.save(ca);
	}

	@Override
	public Map<String, Boolean> deleteConstraint(@PathVariable(value = "idConstraint") Long idConstraint) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Constraint_CO2 c = cr.findByIdConstraint(idConstraint)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown device with reference : " + idConstraint));
		
			cr.delete(c);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted Constraint !", Boolean.TRUE);
		return response;
	}

	@Override
	public ResponseEntity<?> updateConstraint(@PathVariable(value = "idConstraint") Long idConstraint, @Valid @RequestBody Constraint_CO2 c) throws ResourceNotFoundException {
	

		Constraint_CO2 cons = cr.findByIdConstraint(idConstraint)
					.orElseThrow(() -> new ResourceNotFoundException("Unkown constraint with id " +idConstraint));
		cons.setIdConstraint(c.getIdConstraint());
		cons.setMax_value(c.getMax_value());
		cons.setMin_value(c.getMin_value());
		cons.setNameConstraint(c.getNameConstraint());

		
			return new ResponseEntity<>(this.cr.save(cons), HttpStatus.CREATED);
		}

	@Override
	public Map<String, Boolean> deleteArea(Long idArea) throws ResourceNotFoundException {
		Area c = ar.findByIdArea(idArea)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown Area with ID : " + idArea));
		
		ar.delete(c);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted Area !", Boolean.TRUE);
		return response;	}

	@Override
	public Map<String, Boolean> deleteSpace(Long idSpace) throws ResourceNotFoundException {
		Space s = sr.findByIdSpace(idSpace)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown Space with ID : " + idSpace));
		
		sr.delete(s);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted Space !", Boolean.TRUE);
		return response;
	}

	@Override
	public List<ClientArea> listClientArea() {
		// TODO Auto-generated method stub
		return this.car.findAll();	}

	}

	
	

