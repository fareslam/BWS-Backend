package com.pfe.Entity.UserDevices;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.pfe.Entity.Administrator;
import com.pfe.Entity.Device;
import com.pfe.Entity.User;


@Entity
@Table(name="user_devices")
public class UserDevices {
	@EmbeddedId	
	private UDKey udk;
	
	private Date dateAff;
	
	@ManyToOne
    @JoinColumn(name = "cinu",insertable=false,updatable=false)
    private User user;
	
	
	@ManyToOne
    @JoinColumn(name = "reference",insertable=false,updatable=false)
    private Device device;
	
	
	
	@ManyToOne
    @JoinColumn(name = "cin_admin",insertable=false,updatable=false)
    private Administrator administrator;
	private Long cin_admin;
	
	
	public UserDevices() {}
	
	
	public UserDevices(Date d, User user,  Device device,Administrator administrator) {
	
		this.dateAff=d;
		this.user = user;
		this.device = device;
		this.administrator=administrator;
	
	}
	
	
	
	
	public Long getCin_admin() {
		return cin_admin;
	}

	public void setCin_admin(Long cin_admin) {
		this.cin_admin = cin_admin;
	}

	@JsonBackReference(value="UserDeviceAdmin")
	public Administrator getAdministrator() {
		return administrator;
	}


	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}






	public UDKey getUdk() {
		return udk;
	}


	public void setUdk(UDKey udk) {
		this.udk = udk;
	}


	 
	public Date getDateAff() {
		return dateAff;
	}


	public void setDateAff(Date dateAff) {
		this.dateAff = dateAff;
	}


	@JsonBackReference(value="userDevices")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@JsonBackReference(value="DevicesUser")
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

	
	
	
	
	
}