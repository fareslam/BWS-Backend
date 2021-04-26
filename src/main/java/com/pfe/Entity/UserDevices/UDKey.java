package com.pfe.Entity.UserDevices;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UDKey implements Serializable{


	@Column(name="reference")
    private String reference;

	@Column(name="cinu")
    private  Long cinu;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Long getCinu() {
		return cinu;
	}

	public void setCinu(Long cinu) {
		this.cinu = cinu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cinu == null) ? 0 : cinu.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UDKey other = (UDKey) obj;
		if (cinu == null) {
			if (other.cinu != null)
				return false;
		} else if (!cinu.equals(other.cinu))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

	
	
}
