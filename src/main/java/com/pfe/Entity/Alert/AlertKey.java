package com.pfe.Entity.Alert;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AlertKey implements Serializable{

	

	@Column(name="reference")
    private String reference;

	@Column(name="date")
    private  Date date;

	

	public String getReference() {
		return reference;
	}



	public void setReference(String reference) {
		this.reference = reference;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public AlertKey() {

	}



	public AlertKey(String reference, Date date) {
	
		this.reference = reference;
		this.date = date;
	}

	
	
	
	
	

}
