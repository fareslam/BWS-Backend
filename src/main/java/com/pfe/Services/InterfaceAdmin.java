package com.pfe.Services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public Map<String, Boolean> deleteDevice (String reference) throws ResourceNotFoundException, Exception;
	public ResponseEntity<?> addDevice (@Valid @RequestBody Device d);
	public ResponseEntity<?> updateDevice (String reference,Device d) throws ResourceNotFoundException;
	public List<Device> listDevices();
	public ResponseEntity<?> getDeviceByReference(String reference)throws ResourceNotFoundException;
	
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
	  public Map<String, Boolean> deleteConstraint(Long idConstraint)  throws ResourceNotFoundException, Exception;
		public ResponseEntity<?> updateConstraint (Long idConstraint,@Valid @RequestBody Constraint_CO2 c) throws ResourceNotFoundException, Exception;
		  public ResponseEntity<?> getConstraint_CO2(Long idConstraint);
	  
	  /*Area Management*/
	  public Area addArea(Area a);
	  public List<Area> listAreas();
	  public Map<String, Boolean> deleteArea(Long idArea)  throws ResourceNotFoundException;

	  /*Space Management*/
	  public Space addSpace(Space s);
	  public List<Space> listSpaces();
	  public Map<String, Boolean> deleteSpace(Long idSpace)  throws ResourceNotFoundException;
	  public ResponseEntity<?> updateSpace (Long idSpace,Space s) throws ResourceNotFoundException;
	  public Optional<Space> getSpace(Long idSpace);
	  
	  /*Client Area Management*/
	  public ClientArea addClientArea(ClientArea ca);
	  public  List<ClientArea> listClientArea();

}
