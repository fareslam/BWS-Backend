package com.pfe.Entity.History;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HistKey implements Serializable{

	

	@Column(name="idRt")
    private Long idRt;

	@Column(name="idAlert")
    private  Long idAlert;

	public Long getIdRt() {
		return idRt;
	}

	public void setIdRt(Long idRt) {
		this.idRt = idRt;
	}

	public Long getIdAlert() {
		return idAlert;
	}

	public void setIdAlert(Long idAlert) {
		this.idAlert = idAlert;
	}

	public HistKey() {
		super();
	}

	public HistKey(Long idRt, Long idAlert) {

		this.idRt = idRt;
		this.idAlert = idAlert;
	}
	
	
	
	

}
