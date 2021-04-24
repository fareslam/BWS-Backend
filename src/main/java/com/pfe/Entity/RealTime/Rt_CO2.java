package com.pfe.Entity.RealTime;

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

@Entity
@Table(name="rt_co2")
public class Rt_CO2 {
	
	  @EmbeddedId
	 private  RTKey rtk;
	  
	private Date date;
	private Float value_co2;
	

	
	@ManyToOne
    @JoinColumn(name = "reference",insertable=false,updatable=false)
    private Device device;

	
	
	public Rt_CO2(){}
	
	
	public Rt_CO2( Date date, Float value_co2, Device device) {	
		this.date = date;
		this.value_co2 = value_co2;
		this.device = device;
	}


	public RTKey getRtk() {
		return rtk;
	}


	public void setRtk(RTKey rtk) {
		this.rtk = rtk;
	}


	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getValue_co2() {
		return value_co2;
	}
	public void setValue_co2(Float value_co2) {
		this.value_co2 = value_co2;
	}
	

	
	@JsonBackReference(value="DeviceRT")
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

	
	
	
	
}
