package com.pfe.Entity.SubUserSpace;

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
import com.pfe.Entity.Space;
import com.pfe.Entity.SubUser;


@Entity
@Table(name="sub_user_space")
public class SubUser_Space {
	
	  @EmbeddedId
	  private SUS_key sus_key;
	

	@ManyToOne
    @JoinColumn(name = "idSpace",insertable=false,updatable=false)
    private Space space;

	
	@ManyToOne
    @JoinColumn(name = "cin",insertable=false,updatable=false)
    private SubUser sub_user;

	private int Number;
	
	
	
	public SubUser_Space() {}
	
	public SubUser_Space( Space space, SubUser subUser,int number) {
	

		this.space = space;
		this.sub_user = subUser;
		this.Number = number;
	}
	
	

	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	
	
	
	@JsonBackReference(value="SubrSpace")
	public Space getSpace() {
		return space;
	}
	
	public void setSpace(Space space) {
		this.space=space;	
	}

	@JsonBackReference(value="UserSpace")
	public SubUser getSubUser() {
		return sub_user;
	}
	public void setSubUser(SubUser subUser) {
		this.sub_user = subUser;
	}

	public SUS_key getSua_key() {
		return sus_key;
	}

	public void setSus_key(SUS_key sus_key) {
		this.sus_key = sus_key;
	}
	
	
}
