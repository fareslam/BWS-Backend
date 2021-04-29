package com.pfe.Entity.Alert;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pfe.Entity.Device;
import com.pfe.Entity.History.HistKey;
import com.pfe.Entity.History.History_CO2;
import com.pfe.Entity.RealTime.Rt_CO2;

@Entity
@Table(name="alert_co2")
public class Alert_C02 {
	 /* @EmbeddedId
	  private  AlertKey ak;*/


	@Id
	private Date date;
	

	private String reference;
	
	
	private String message;
	



	/*public AlertKey getAk() {
		return ak;
	}
	public void setAk(AlertKey ak) {
		this.ak = ak;
	}*/
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}



public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
public Alert_C02() {}

public Alert_C02( Date date, String r, String message) {
	super();

	this.date = date;
	this.reference=r;

	this.message = message;
}

	
	
}
