package com.pfe.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pfe.Entity.UserDevices.UserDevices;

@Entity
@Table(name="administrator")
public class Administrator {

	@Id
	private Long cin_admin;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true )
	@JoinColumn(name = "admin_id")
	private SubUser subuser;
	
	@OneToMany(mappedBy="administrator",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<User> list_users;
	
	@OneToMany(mappedBy="administrator",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserDevices> list_user_dev;

	
	
	public Administrator() {}


	public Administrator(Long cin, SubUser subuser) {
		this.cin_admin = cin;
		this.subuser = subuser;
	}

	public Long getCin_admin() {
		return cin_admin;
	}

	public void setCin_admin(Long cin) {
		this.cin_admin = cin;
	}

	public SubUser getSubuser() {
		return subuser;
	}

	public void setSubuser(SubUser subuser) {
		this.subuser = subuser;
	}

	@JsonManagedReference(value="ua")
	public List<User> getList_users() {
		return list_users;
	}

	public void setList_users(List<User> list_users) {
		this.list_users = list_users;
	}

	@JsonManagedReference(value="UserDeviceAdmin")
	public List<UserDevices> getList_user_dev() {
		return list_user_dev;
	}

	public void setList_user_dev(List<UserDevices> list_user_dev) {
		this.list_user_dev = list_user_dev;
	}



	
}
