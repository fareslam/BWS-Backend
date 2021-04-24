package com.pfe.Entity.History;

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
import com.pfe.Entity.Device;
import com.pfe.Entity.Alert.Alert_C02;
import com.pfe.Entity.RealTime.Rt_CO2;

@Entity
@Table(name="history_co2")
public class History_CO2 {
	
	  @EmbeddedId
	 private  HistKey hk;
	  
	private Float value;
	

	public HistKey getHk() {
		return hk;
	}


	public void setHk(HistKey hk) {
		this.hk = hk;
	}


	public History_CO2() {}
	
	
	public History_CO2(Float v) 
	{this.value=v;}
	
	
	
	
	public Float getValue() {
		return value;
	}


	public void setValue(Float value) {
		this.value = value;
	}

	
	


}
