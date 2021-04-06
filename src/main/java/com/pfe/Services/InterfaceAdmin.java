package com.pfe.Services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Entity.Area;
import com.pfe.Entity.Constraint_CO2;
import com.pfe.Entity.Device;
import com.pfe.Entity.SignupRequest;
import com.pfe.Entity.Space;
import com.pfe.Entity.User;
import com.pfe.Entity.ClientArea.ClientArea;
import com.pfe.Entity.UserDevices.UserDevices;
import com.pfe.exception.ResourceNotFoundException;

public interface InterfaceAdmin {
	
	/* Device Management */
	public Map<String, Boolean> deleteDevice (String reference) throws ResourceNotFoundException;
	public ResponseEntity<?> addDevice (@Valid @RequestBody Device d);
	public ResponseEntity<?> updateDevice (String reference,Device d) throws ResourceNotFoundException;
	public List<Device> listDevices();
	public Device getDeviceByReference(String reference)throws ResourceNotFoundException;
	
	/* User Management */
	public Map<String, Boolean> deleteUser(Long cinu)  throws ResourceNotFoundException;
	public ResponseEntity<?> addUser (SignupRequest signUpRequest);
	public List<User> listUsers();
	public User getUserByCin(Long Cin)throws ResourceNotFoundException;
	
	/* User Device Management */
	  public ResponseEntity<?> affectUserDevice(@Valid @RequestBody UserDevices ud);
	  public List<UserDevices> ListUserDevices();
	  
	  /*Constraint managment */
	  public Constraint_CO2 addConstraint(@Valid @RequestBody Constraint_CO2 c);
	  public List<Constraint_CO2>listConstraints();
	  public Map<String, Boolean> deleteConstraint(Long idConstraint)  throws ResourceNotFoundException;
		public ResponseEntity<?> updateConstraint (Long idConstraint,@Valid @RequestBody Constraint_CO2 c) throws ResourceNotFoundException;

	  
	  /*Area Management*/
	  public Area addArea(Area a);
	  public List<Area> listAreas();

	  /*Space Management*/
	  public Space addSpace(Space s);
	  public List<Space> listSpaces();
	  
	  /*Client Area Management*/
	  public ClientArea addClientArea(ClientArea ca);
}