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
import com.pfe.Entity.Alert_C02;
import com.pfe.Entity.Rt_CO2;

@Entity
@Table(name="history_co2")
public class History_CO2 {
	
	  @EmbeddedId
	 private  HistKey hk;
	  
	private Date dateHistory;
	
	@ManyToOne
    @JoinColumn(name = "idRt",insertable=false,updatable=false)
    private Rt_CO2 rt_co2;
	
	
	@ManyToOne
    @JoinColumn(name = "idAlert",insertable=false,updatable=false)
    private Alert_C02 alert_co2;

	
	
	
	public HistKey getHk() {
		return hk;
	}


	public void setHk(HistKey hk) {
		this.hk = hk;
	}


	public History_CO2() {}
	
	
	public History_CO2( Date dateHistory, Rt_CO2 rt_co2, Alert_C02 alert_co2) {

	
		this.dateHistory = dateHistory;
		this.rt_co2 = rt_co2;
		this.alert_co2 = alert_co2;
	}
	
	
	
	
	public Date getDateHistory() {
		return dateHistory;
	}
	
	public void setDateHistory(Date dateHistory) {
		this.dateHistory = dateHistory;
	}
	
	@JsonBackReference(value="HistRT")
	public Rt_CO2 getRt_co2() {
		return rt_co2;
	}
	
	public void setRt_co2(Rt_CO2 rt_co2) {
		this.rt_co2 = rt_co2;
	}
	

	
	@JsonBackReference(value="HistAlert")
	public Alert_C02 getAlert_co2() {
		return alert_co2;
	}
	
	public void setAlert_co2(Alert_C02 alert_co2) {
		this.alert_co2 = alert_co2;
	}
	


}
