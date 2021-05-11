package com.pfe.Controllers;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Email.MailResponse;
import com.pfe.Entity.Device;
import com.pfe.Entity.MessageResponse;
import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.Space;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;
import com.pfe.Entity.Alert.Alert_C02;
import com.pfe.Entity.History.History_CO2;
import com.pfe.Repository.AdministratorRepository;
import com.pfe.Repository.AlertRepository;
import com.pfe.Repository.DeviceRepository;
import com.pfe.Repository.SubUserRepository;
import com.pfe.Repository.UserRepository;
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

	@Autowired
	DeviceRepository dr;  
	
	@Autowired
	SubUserRepository sur;
	
	@Autowired
	AlertRepository alr;
	
	@Autowired
	UserRepository ur;

	@GetMapping("/report/alert/{cin}/{reference}")
	public List<Alert_C02> reportAlertByRef(@PathVariable(value = "cin") Long cin,@PathVariable(value = "reference") String reference) throws ResourceNotFoundException,Exception {
		SubUser u = sur.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cin));
		Device d = dr.findByReference(reference);
	if (d==null) {throw new Exception ("unkown device with ref "+reference);}
		
		return this.sur.reportAlertByRef(cin, reference);
	}
	


	
	@GetMapping("/report/history/{cin}/{reference}")
	public List<History_CO2> reportHistoryByRef(@PathVariable(value = "cin") Long cin,@PathVariable(value = "reference") String reference) throws ResourceNotFoundException,Exception {
		SubUser u = sur.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cin));
		Device d = dr.findByReference(reference);
	if (d==null) {throw new Exception ("unkown device with ref "+reference);}
		
		return this.sur.reportHistoryByRef(cin, reference);
	}
	
	
	@GetMapping("/report/historyAlert/{cin}/{reference}")
	public List<?> reportHistoryAlertByRef(@PathVariable(value = "cin") Long cin,@PathVariable(value = "reference") String reference) throws ResourceNotFoundException,Exception {
		SubUser u = sur.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Unkown User with CIN : " + cin));
		Device d = dr.findByReference(reference);
	if (d==null) {throw new Exception ("unkown device with ref "+reference);}
		
		return this.sur.reportHistoryAlertByRef(cin, reference);
	}
	
	
	
	
	/* ***List Spaces ***********/
	@GetMapping("/spaces/{cin}")	
	public List<Space> listSpace(@PathVariable(value = "cin") Long cin) 	
	throws ResourceNotFoundException{
		
		return this.sur.listSubUsersSpace(cin);
	}
	
	
	
	/* ***List Devices ***********/
	@GetMapping("/devices/{cin}")	
	public List<Device> listDevice(@PathVariable(value = "cin") Long cin) 	
	throws ResourceNotFoundException{
		
		return this.sur.listDevices(cin);
	}
	
	
	
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
