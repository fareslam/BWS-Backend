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
import com.pfe.Entity.ClientArea.ClientArea;

@Entity
@Table(name="area")
public class Area {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idArea;

	private Float longitude;
	private Float latitude;
	private String name;
	
	@OneToMany(mappedBy="area",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Space> list_sapces;
	

	@OneToMany(mappedBy="area",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ClientArea> list_clients_area;
	
	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonManagedReference(value="AreaSpace")
	public List<Space> getList_sapces() {
		return list_sapces;
	}

	public void setList_sapces(List<Space> list_sapces) {
		this.list_sapces = list_sapces;
	}

	@JsonManagedReference(value="SubrcArea")
	public List<ClientArea> getList_clients_area() {
		return list_clients_area;
	}

	public void setList_clients_area(List<ClientArea> list_clients_area) {
		this.list_clients_area = list_clients_area;
	}

	public long getIdArea() {
		return idArea;
	}

	


	public void setIdArea(long id) {
		this.idArea = id;
	}
	
}
