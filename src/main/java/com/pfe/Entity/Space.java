package com.pfe.Entity;

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
import com.pfe.Entity.SubUserSpace.SubUser_Space;

@Entity
@Table(name="space")
public class Space {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idSpace;

	private Float longitude;
	private Float latitude;
	
	private String name;
	
	@OneToMany(mappedBy="space",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Device> list_devices_space;
	
	@ManyToOne
    @JoinColumn(name = "idArea",insertable=false,updatable=false)
    private Area area;
	private Long idArea;
	
	@OneToMany(mappedBy="space",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<SubUser_Space> list_sub_space;

	

	public long getIdSpace() {
		return idSpace;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setIdSpace(long idSpace) {
		this.idSpace = idSpace;
	}

	@JsonBackReference(value="AreaSpace")
	public Area getArea() {
		return area;
	}

	@JsonManagedReference(value="SubrSpace")
	public List<SubUser_Space> getList_sub_space() {
		return list_sub_space;
	}


	public void setList_sub_space(List<SubUser_Space> list_sub_space) {
		this.list_sub_space = list_sub_space;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	public Long getIdArea() {
		return idArea;
	}


	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}


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

	@JsonManagedReference(value="DeviceSpace")
	public List<Device> getList_devices_space() {
		return list_devices_space;
	}


	public void setList_devices_space(List<Device> list_devices_space) {
		this.list_devices_space = list_devices_space;
	}
	
	
	
	
	
}
