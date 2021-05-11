	package com.pfe.Controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Entity.Device;
import com.pfe.Entity.MessageResponse;
import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.Space;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;
import com.pfe.Entity.Alert.Alert_C02;
import com.pfe.Entity.ClientArea.ClientArea;
import com.pfe.Entity.History.History_CO2;
import com.pfe.Entity.SubUserSpace.SubUser_Space;
import com.pfe.Repository.AdministratorRepository;
import com.pfe.Repository.AlertRepository;
import com.pfe.Repository.DeviceRepository;
import com.pfe.Repository.SpaceRepository;
import com.pfe.Repository.SubUserRepository;
import com.pfe.Repository.UserRepository;
import com.pfe.Services.InterfaceUser;
import com.pfe.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/api/bws/user")
@CrossOrigin
public class UserController {

	@Autowired
	DeviceRepository dr;  
	
	@Autowired
	SubUserRepository sur;
	
	@Autowired
	AlertRepository alr;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	AdministratorRepository ar;
	
	@Autowired
	SpaceRepository sr;
	
	@Autowired
	InterfaceUser iu;
	
	
	/* ********** Sub User Management *********/
	@PostMapping("/{cinu}/SubUser/add")
	public ResponseEntity<?> addSubUser(@PathVariable(value = "cinu") Long cinu,@Valid @RequestBody SignupRequest signUpRequest) throws ResourceNotFoundException {
		return this.iu.addSubUser(cinu,signUpRequest);
	}
	
	@PutMapping("/{cinu}/{cin}/SubUser/update")
	public ResponseEntity<?> updateSubUser(@PathVariable(value = "cinu") Long cinu,@PathVariable(value = "cin") Long cin,@Valid @RequestBody SignupRequest signUpRequest) throws ResourceNotFoundException {
		return this.iu.UpdateSubUser(cinu,cin,signUpRequest);
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
	
	@GetMapping("/report/alert/{cinu}/{reference}")
	public List<Alert_C02> reportAlertByRef(@PathVariable(value = "cinu") Long cinu,@PathVariable(value = "reference") String reference) throws ResourceNotFoundException,Exception {
		User u = ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		Device d = dr.findByReference(reference);
	if (d==null) {throw new Exception ("unkown device with ref "+reference);}
		
		return this.ur.reportAlertByRef(cinu, reference);
	}
	
/*	@GetMapping("/report/alert/date/{cinu}/{date}")
	public List<Alert_C02 >rAlertByDate(@PathVariable(value = "cinu") Long cinu,
			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) throws ResourceNotFoundException{
		User u = ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		
		
	
		return this.ur.reportAlertByDate(cinu, date);
	}*/
	
	
	@GetMapping("/report/history/{cinu}/{reference}")
	public List<History_CO2> reportHistoryByRef(@PathVariable(value = "cinu") Long cinu,@PathVariable(value = "reference") String reference) throws ResourceNotFoundException,Exception {
		User u = ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		Device d = dr.findByReference(reference);
	if (d==null) {throw new Exception ("unkown device with ref "+reference);}
		
		return this.ur.reportHistoryByRef(cinu, reference);
	}
	
	
	@GetMapping("/report/historyAlert/{cinu}/{reference}")
	public List<?> reportHistoryAlertByRef(@PathVariable(value = "cinu") Long cinu,@PathVariable(value = "reference") String reference) throws ResourceNotFoundException,Exception {
		User u = ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		Device d = dr.findByReference(reference);
	if (d==null) {throw new Exception ("unkown device with ref "+reference);}
		
		return this.ur.reportHistoryAlertByRef(cinu, reference);
	}
	
	@GetMapping("/{cinu}/devices")
	public List<Device> listDevicesUser(@PathVariable(value = "cinu") Long cinu) throws ResourceNotFoundException {
		User u = ur.findByCinu(cinu)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		
		return this.ur.listDevicesPerUser(cinu);
	}
	
	
	
	@GetMapping("/{cin}")
	public Optional<User> UserBySubUser(@PathVariable(value = "cin") Long cin) throws ResourceNotFoundException {
				return this.ur.userBySubUser(cin);
	}
	
	  /* ******** Sub User space managment ****************/
	  @PostMapping("/SubUserSpace/add")
	  public SubUser_Space addSubUserSpace(@Valid @RequestBody SubUser_Space ss)  {
		  return this.iu.addSubUser_Space(ss);
	  }  
	  
	  @GetMapping("/SubUserSpace/all")
	  public List<SubUser_Space> listSubUserSpaces()  {
		  return this.iu.listSubUserspaces();
	  } 
	  
		@GetMapping("/{cinu}/SubUserSpace/all")
		public List<SubUser_Space> listSubUserSpacePerUser(@PathVariable(value = "cinu") Long cinu) throws ResourceNotFoundException {
			User u = ur.findByCinu(cinu)
					.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cinu));
			
			return this.ur.listSubUsersInSpaceByUser(cinu);
		}
		
	
		
		  @DeleteMapping("/SubUserSpace/delete/{cinu}/{cin}/{idSpace}")
		  public Map<String, Boolean> SubUser_SpaceByUser(@PathVariable(value = "cinu") Long cinu,@PathVariable(value = "cin") Long cin,@PathVariable(value = "idSpace") Long idSpace) 
				  throws Exception,ResourceNotFoundException {
			return this.iu.deleteSubUserSpace(cinu, cin, idSpace);
		  } 
		
		
		
	  @GetMapping("/SpacesPerUserDevice/{cinu}")
	  public List<?> listSubUserSpacesDevice(@PathVariable(value = "cinu") Long cinu) throws ResourceNotFoundException {
			User u = ur.findByCinu(cinu)
					.orElseThrow(() ->new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		  return this.ur.listSpacesPerUserDevice(cinu);
	  } 
	  
	  @GetMapping("/SpacesPerUserDevice2/{cinu}")
	  public List<Space> listSubUserSpacesDevices(@PathVariable(value = "cinu") Long cinu) throws ResourceNotFoundException {
			User u = ur.findByCinu(cinu)
					.orElseThrow(() ->new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		  return this.ur.ScndlistSpacesPerUserDevice(cinu);
	  } 
	  
	  
	  @GetMapping("/AreasPerUser/{cinu}")
	  public List<?> listAreasPerUser(@PathVariable(value = "cinu") Long cinu) throws ResourceNotFoundException {
			User u = ur.findByCinu(cinu)
					.orElseThrow(() ->new ResourceNotFoundException("Unkown User with CIN : " + cinu));
		  return this.ur.listAreasPerUser(cinu);
	  } 
	  
	  
		/* *** Real Time Managment ***********/
		@GetMapping("/rt/{reference}")	
		public List<Float> listValRT(@PathVariable(value = "reference") String reference) 	
		throws ResourceNotFoundException{
			
			return this.ar.AllvaluesRT(reference);
		}
		 
}
