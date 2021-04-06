package com.pfe.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pfe.Entity.ClientArea.ClientArea;
import com.pfe.Entity.SubUserSpace.SubUser_Space;
import com.pfe.Entity.UserDevices.UserDevices;



@Entity
@Table(name="user", 
uniqueConstraints = { 
		
		@UniqueConstraint(columnNames = "cinu")

		})
public class User {
	@Id
	private long cinu;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true )
	@JoinColumn(name = "user_id")
	private SubUser subuser;
	

	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<SubUser> list_sub_users;
	
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserDevices> list_userDevices;
	
	
	@ManyToOne
    @JoinColumn(name = "cin_admin",insertable=false,updatable=false)
    private Administrator administrator;
	private Long cin_admin;
	
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ClientArea> list_client_areas;
	
 
	public User(long cin, SubUser subuser, Administrator administrator) {
	
		this.cinu = cin;
		this.subuser = subuser;
		this.administrator = administrator;
	}

	public User() {

	}

	
	
	public long getCinu() {
		return cinu;
	}

	public void setCinu(long cinu) {
		this.cinu = cinu;
	}

	@JsonManagedReference(value="uuu")
	public List<SubUser> getList_sub_users() {
		return list_sub_users;
	}

	public void setList_sub_users(List<SubUser> list_sub_users) {
		this.list_sub_users = list_sub_users;
	}

	public Long getCin_admin() {
		return cin_admin;
	}

	public void setCin_admin(Long cin_admin) {
		this.cin_admin = cin_admin;
	}

	@JsonManagedReference(value="userDevices")
	public List<UserDevices> getList_userDevices() {
		return list_userDevices;
	}

	public void setList_userDevices(List<UserDevices> list_userDevices) {
		this.list_userDevices = list_userDevices;
	}

	public SubUser getSubuser() {
		return subuser;
	}

	public void setSubuser(SubUser subuser) {
		this.subuser = subuser;
	}
	
	
	@JsonBackReference(value="ua")
	public Administrator getAdministrator() {
		return administrator;
	}





	@JsonManagedReference(value="UserAreas")
	public List<ClientArea> getList_client_areas() {
		return list_client_areas;
	}

	public void setList_client_areas(List<ClientArea> list_client_areas) {
		this.list_client_areas = list_client_areas;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}


	public void setCin(long cin) {
		this.cinu = cin;
	}


	public Long getCin() {
		return cinu;
	}

	public void setCin(Long cin) {
		this.cinu = cin;
	}

	public SubUser getSubUser() {
		return subuser;
	}

	public void setSubUser(SubUser user) {
		this.subuser = user;
	}



	
	
}
