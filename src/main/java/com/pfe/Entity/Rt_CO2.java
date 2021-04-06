package com.pfe.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import com.pfe.Entity.History.History_CO2;

@Entity
@Table(name="rt_co2")
public class Rt_CO2 {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idRt;
	private Date date;
	private Float value_co2;
	
	
	@OneToMany(mappedBy="rt_co2",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<History_CO2> list_history;
	
	
	@ManyToOne
    @JoinColumn(name = "reference",insertable=false,updatable=false)
    private Device device;
	private String reference;
	
	
	public Rt_CO2(){}
	
	
	public Rt_CO2(long idRt, Date date, Float value_co2, Device device) {
		super();
		this.idRt = idRt;
		this.date = date;
		this.value_co2 = value_co2;
		this.device = device;
	}
	public long getIdRt() {
		return idRt;
	}
	public void setIdRt(long idRt) {
		this.idRt = idRt;
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
	
	@JsonManagedReference(value="HistRT")
	public List<History_CO2> getList_history() {
		return list_history;
	}
	public void setList_history(List<History_CO2> list_history) {
		this.list_history = list_history;
	}
 
	
	@JsonBackReference(value="DeviceRT")
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	
	
	
}
