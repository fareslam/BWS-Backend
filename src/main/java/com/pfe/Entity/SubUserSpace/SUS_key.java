package com.pfe.Entity.SubUserSpace;
	

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;



@Embeddable
public class SUS_key  implements Serializable{


			@Column(name="idSpace")
		    private Long idSpace;

			@Column(name="cin")
		    private  Long cin;

			public Long getIdSpace() {
				return idSpace;
			}

			public void setIddSpace(Long dSpace) {
				this.idSpace = idSpace;
			}

			public Long getCin() {
				return cin;
			}

			public void setCin(Long cin) {
				this.cin = cin;
			}


			
		    
		    
	}


