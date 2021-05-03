package com.pfe.Entity;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
 
public class SignupRequest {
	
	private long cin;
	private String username;
	@NotBlank
	private String password;
	private String Email;
	@JsonFormat(pattern= "yyyy-MM-dd")
	private Date dateBirth;
	private String name;
	private String surname;
	private long tel;
	private Long cin_admin;
	private Long cinu;
	private String imageurl;
    private Set<String> role;


	public Long getCinu() {
		return cinu;
	}
	public void setCinu(Long cinu) {
		this.cinu = cinu;
	}
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}
	public Long getCin_admin() {
		return cin_admin;
	}
	public void setCin_admin(Long cin_admin) {
		this.cin_admin = cin_admin;
	}
	public long getCin() {
		return cin;
	}
	public void setCin(long cin) {
		this.cin = cin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	
	
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public long getTel() {
		return tel;
	}
	public void setTel(long tel) {
		this.tel = tel;
	}
	
	
	
	
}
