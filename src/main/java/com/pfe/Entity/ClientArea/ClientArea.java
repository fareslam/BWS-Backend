package com.pfe.Entity.ClientArea;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pfe.Entity.Area;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;


@Entity
@Table(name="client_area")
public class ClientArea {
	
	  @EmbeddedId
	  private UA_key ua_key;
	

	@ManyToOne
    @JoinColumn(name = "idArea",insertable=false,updatable=false)
    private Area area;

	
	@ManyToOne
    @JoinColumn(name = "cinu",insertable=false,updatable=false)
    private User user;

	private int Number;
	
	
	
	public ClientArea() {}
	

	

	public ClientArea(Area area, User user, int number) {
		super();
		this.area = area;
		this.user = user;
		Number = number;
	}




	public UA_key getUA_key() {
		return ua_key;
	}




	public void setUA_key(UA_key ua_key) {
		this.ua_key = ua_key;
	}



	@JsonBackReference(value="SubrcArea")
	public Area getArea() {
		return area;
	}




	public void setArea(Area area) {
		this.area = area;
	}



	@JsonBackReference(value="UserAreas")
	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	
	
	
	
	
}
