package com.pfe.Entity.ClientArea;
	

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;



@Embeddable
public class UA_key  implements Serializable{


			@Column(name="idArea")
		    private Long idArea;

			@Column(name="cinu")
		    private  Long cinu;

			public Long getIdArea() {
				return idArea;
			}

			public void setIdArea(Long idArea) {
				this.idArea = idArea;
			}

			public Long getCinu() {
				return cinu;
			}

			public void setCin(Long cinu) {
				this.cinu = cinu;
			}


			
		    
		    
	}


