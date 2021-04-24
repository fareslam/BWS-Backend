package com.pfe.Entity.Alert;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pfe.Entity.History.HistKey;
import com.pfe.Entity.History.History_CO2;

@Entity
@Table(name="alert_co2")
public class Alert_C02 {
	  @EmbeddedId
	  private  AlertKey ak;

	private String message;



	public AlertKey getAk() {
		return ak;
	}
	public void setAk(AlertKey ak) {
		this.ak = ak;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Alert_C02(String message) {

		this.message = message;
	}

public Alert_C02() {}
	
	
}
