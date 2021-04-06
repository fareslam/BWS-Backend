package com.pfe.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alert_co2")
public class Alert_C02 {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idAlert;
	private String message;
	
	
	
}
