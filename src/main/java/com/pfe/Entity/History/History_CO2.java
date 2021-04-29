package com.pfe.Entity.History;

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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pfe.Entity.Device;
import com.pfe.Entity.Alert.Alert_C02;
import com.pfe.Entity.RealTime.Rt_CO2;

@Entity
@Table(name="history_co2")
public class History_CO2 {
	
	/*  @EmbeddedId
	 private  HistKey hk;*/

	@Id
	private Date date;
	private Float value;
	

	private String reference;
	

	/*@ManyToOne
	@MapsId("rtk")
    private Rt_CO2 rt_co2;
	*/
	
	
/*
	
	private String rt_co2_reference;
	
	

	public String getRt_co2_reference() {
		return rt_co2_reference;
	}

	public void setRt_co2_reference(String rt_co2_reference) {
		this.rt_co2_reference = rt_co2_reference;
	}*/

	
	
	

/*
	public HistKey getHk() {
		return hk;
	}


	public void setHk(HistKey hk) {
		this.hk = hk;
	}*/

	

	public History_CO2() {}
	
	
	
	
	





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




	public Float getValue() {
		return value;
	}


	public void setValue(Float value) {
		this.value = value;
	}




	public History_CO2( Date date, Float value,String r) {
		super();
	
		this.date = date;
		this.value = value;
		this.reference=r;
		
	}

	
	


}
