package com.pfe.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Entity.Administrator;
import com.pfe.Entity.Area;
import com.pfe.Entity.Constraint_CO2;
import com.pfe.Entity.Device;
import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.Space;
import com.pfe.Entity.User;
import com.pfe.Entity.ClientArea.ClientArea;
import com.pfe.Entity.UserDevices.UDKey;
import com.pfe.Entity.UserDevices.UserDevices;
import com.pfe.Repository.AdministratorRepository;
import com.pfe.Repository.DeviceRepository;
import com.pfe.Repository.SubUserRepository;
import com.pfe.Repository.UserDevicesRepository;
import com.pfe.Repository.UserRepository;
import com.pfe.Services.InterfaceAdmin;
import com.pfe.Services.InterfaceUser;
import com.pfe.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/bws/admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {

	@Autowired
	SubUserRepository sur;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	UserDevicesRepository udr;
	
	@Autowired
	DeviceRepository dr;
	
	@Autowired
	AdministratorRepository ar;
	
	@Autowired
	InterfaceAdmin ia;
	
	
	@GetMapping("/{cin}")
	public Optional<Administrator> getAdmin(@PathVariable(value = "cin")Long cin){
		return this.ar.adminBySubUser(cin);
		
		
	}
	

	/* ************** Device Management ******************/
	@DeleteMapping("/device/delete/{reference}")
	public Map<String, Boolean>  deleteDevice(@PathVariable(value = "reference") String reference) throws ResourceNotFoundException, Exception{
		
		return this.ia.deleteDevice(reference);
	}
	
	
	
	@PostMapping("/device/add")
	public ResponseEntity<?> addDevice(@Valid @RequestBody Device d) {
		return this.ia.addDevice(d);
	}
	
	
	
	
	@PutMapping("/device/update/{reference}")	
	public ResponseEntity<?> updateDevice(@PathVariable(value = "reference") String reference, @Valid @RequestBody Device d) 	
	throws ResourceNotFoundException{
		
		return this.ia.updateDevice(reference, d);
	}
	
	@PutMapping("/device/ct/update/{reference}")	
	public ResponseEntity<?> updateDeviceConstraint(@PathVariable(value = "reference") String reference, @Valid @RequestBody Device d) 	
	throws ResourceNotFoundException{
		
		return this.ia.updateDeviceConstraint(reference, d);
	}
	
	
	@GetMapping("/device/all")	
	public List<Device> listDevices(){
		return this.ia.listDevices();
	}
	
	@GetMapping("/device/ct/all")	
	public List<Device> listDevicesCT(){
		return this.ar.devicesbyIdConstraint();	
	}
	
	@GetMapping("/device/{reference}")	
	public ResponseEntity<?>  getDeviceByReference(@PathVariable(value = "reference") String reference)throws ResourceNotFoundException{
		return this.ia.getDeviceByReference(reference);
		
		
	}
	
	
	
	/* ************** User Management ******************/
	@DeleteMapping("/user/delete/{cinu}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "cinu") Long cinu)  throws ResourceNotFoundException{
		
		return this.ia.deleteUser(cinu);
	}
	
	@PostMapping("/user/add")
	public ResponseEntity<?> addUser (@RequestBody @Valid SignupRequest signUpRequest){
		return this.ia.addUser(signUpRequest);
		
	}
	
	@GetMapping("/user/all")	
	public List<User> listUsers(){
		
		return this.ia.listUsers();
	}
	
	
	@GetMapping("/user/{cinu}")	
	public User getUserByCin(@PathVariable(value = "cinu") Long Cinu)throws ResourceNotFoundException{
		return this.ia.getUserByCin(Cinu);
		
	}
	
	
	
	
	
	/* ************User Device Management ************/
	@PostMapping("/userDevices/add")
	  public ResponseEntity<?> affectUserDevice(@Valid @RequestBody UserDevices ud){
		  
		  return this.ia.affectUserDevice(ud);
	  }
	
	

	
	
	@DeleteMapping("/userDevices/delete/{cinu}/{reference}")
	  public Map<String, Boolean>  deleteUserDevice(@PathVariable(value = "cinu") Long cinu,
			  @PathVariable(value = "reference") String reference) throws ResourceNotFoundException,Exception {
	return this.ia.deleteUserDevice(cinu, reference);
		
	  }
	  
	@GetMapping("/userDevices/all")  
	 public List<UserDevices> listuserDevices()
	 {
		return this.ia.ListUserDevices();
	 }
	  
	  /* ********Constraint managment **************/
	@PostMapping("/constraint/add")
	  public ResponseEntity<?> addConstraint(@Valid @RequestBody Constraint_CO2 c) {
		  
		  return this.ia.addConstraint(c);
		  
	  }
	
	
	
	@PutMapping("/constraint/update/{idConstraint}")	
	public ResponseEntity<?> updateConstraint(@PathVariable(value = "idConstraint") Long idConstraint, @Valid @RequestBody Constraint_CO2 c) throws ResourceNotFoundException, Exception{
		
		return this.ia.updateConstraint(idConstraint, c);
	}
	
	  @GetMapping("/constraint/{idConstraint}")
	  public    ResponseEntity<?> getConstraint(@PathVariable(value = "idConstraint") Long idConstraint) {
		  
		  return this.ia.getConstraint_CO2(idConstraint);
		  
	  }
	
	@DeleteMapping("/constraint/delete/{idConstraint}")
	  public  Map<String, Boolean> deleteConstraint(@PathVariable(value = "idConstraint") Long idConstraint) throws ResourceNotFoundException, Exception{
		  
		  return this.ia.deleteConstraint(idConstraint);
		  
	  }
	  
	@GetMapping("/constraint/all")
	  public List<Constraint_CO2>listConstraints(){
		  return this.ia.listConstraints();
	  }
	
	
	  /* ******** Area managment ****************/
	  @PostMapping("/area/add")
	  public Area addArea(@Valid @RequestBody Area a)  {
		  return this.ia.addArea(a);
	  }
	  
	  
		@GetMapping("/area/all")
		  public List<Area>listAreas(){
			  return this.ia.listAreas();
		  }
	  
	
		
		@DeleteMapping("/area/delete/{idArea}")
		  public  Map<String, Boolean> deleteArea(@PathVariable(value = "idArea") Long idArea) throws ResourceNotFoundException{
			  
			  return this.ia.deleteArea(idArea);
			  
		  }
	  /* ******** ClientArea managment ****************/
	  @PostMapping("/clientArea/add")
	  public ClientArea addClientArea(@Valid @RequestBody ClientArea ca)  {
		  return this.ia.addClientArea(ca);
	  }  
	  
	  @GetMapping("/clientArea/all")
	  public List<ClientArea> listClientArea()  {
		  return this.ia.listClientArea();
	  } 
	  
		@DeleteMapping("/clientArea/delete/{cinu}/{idArea}")
		  public Map<String, Boolean>  deleteUserDevice(@PathVariable(value = "cinu") Long cinu,
				  @PathVariable(value = "idArea") Long idArea) throws ResourceNotFoundException,Exception {
		return this.ia.deleteClientArea(cinu, idArea);
			
		  }

	  /* ******** Space managment ****************/
	  @PostMapping("/space/add")
	  public Space addSpace(@Valid @RequestBody Space s)  {
		  return this.ia.addSpace(s);
	  } 

	  @GetMapping("/space/all")
	  public List<Space> addSpace()  {
		  return this.ia.listSpaces();
	  } 
	  @GetMapping("/space/{idSpace}")
	  public   Optional<Space> getSpace(@PathVariable(value = "idSpace") Long idSpace) {
		  
		  return this.ia.getSpace(idSpace);
		  
	  }
  
		@DeleteMapping("/space/delete/{idSpace}")
		  public  Map<String, Boolean> deleteSpace(@PathVariable(value = "idSpace") Long idSpace) throws ResourceNotFoundException{
			  
			  return this.ia.deleteSpace(idSpace);
			  
		  }
	  
		@PutMapping("/space/update/{idSpace}")	
		public ResponseEntity<?> updateDevice(@PathVariable(value = "idSpace") Long idSpace, @Valid @RequestBody Space s) 	
		throws ResourceNotFoundException{
			
			return this.ia.updateSpace(idSpace, s);
		}
		
}
	
	

