package com.pfe.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="constraint_co2")
public class Constraint_CO2 {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idConstraint;
	private Float min_value;
	private String nameConstraint;
	private Float max_value;
	
	
	@OneToMany(mappedBy="constraint_co2",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Device> list_dev_constraints;


	
	public Constraint_CO2() {}
	
	public Constraint_CO2(Float min_value, Float max_value) {
		super();
		this.min_value = min_value;
		this.max_value = max_value;
	}


	public long getIdConstraint() {
		return idConstraint;
	}


	public String getNameConstraint() {
		return nameConstraint;
	}

	public void setNameConstraint(String nameConstraint) {
		this.nameConstraint = nameConstraint;
	}

	public void setIdConstraint(long idConstraint) {
		this.idConstraint = idConstraint;
	}


	public Float getMin_value() {
		return min_value;
	}


	public void setMin_value(Float min_value) {
		this.min_value = min_value;
	}


	public Float getMax_value() {
		return max_value;
	}


	public void setMax_value(Float max_value) {
		this.max_value = max_value;
	}

	@JsonManagedReference(value="deviconstraint")
	public List<Device> getList_dev_constraints() {
		return list_dev_constraints;
	}


	public void setList_dev_constraints(List<Device> list_dev_constraints) {
		this.list_dev_constraints = list_dev_constraints;
	}

	
	
}
