package com.pfe.Response;

import java.util.Date;
import java.util.List;


public class JwtResponse {
	private String Token;
/*	private String type = "Bearer";*/
	private Long cin;
	private String username;
	private String name;
	private String surname;
	private long tel;
	private Date dateBirth;


	private String email;
	private List<String> roles;
	private String imageurl;
	public JwtResponse(String Token, Long cin, String username,String nom,String prenom,long tel,Date dateNaissance, String email,
			List<String> roles,String imageurl) {
		this.Token = Token;
		this.cin = cin;
		this.username = username;
		this.name=nom;
		this.surname=prenom;
		this.tel=tel;
		this.dateBirth=dateNaissance;
		this.email = email;
		this.roles = roles;
		this.imageurl=imageurl;
	}

	

	public String getImageurl() {
		return imageurl;
	}



	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}



	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	
	
	
	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		this.Token = token;
	}
/*
	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}*/

	


	public String getEmail() {
		return email;
	}

	public Long getCin() {
		return cin;
	}



	public void setCin(Long cin) {
		this.cin = cin;
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



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public Date getDateBirth() {
		return dateBirth;
	}



	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}



	public void setRoles(List<String> roles) {
		this.roles = roles;
	}



	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
