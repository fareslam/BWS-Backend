package com.pfe.Entity.RealTime;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RTKey implements Serializable{
	@Column(name="reference")
    private String reference;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public RTKey(String reference) {
	
		this.reference = reference;
	}
	
	
	public RTKey() {}
	
}
