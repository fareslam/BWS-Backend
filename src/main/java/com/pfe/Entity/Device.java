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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pfe.Entity.UserDevices.UserDevices;

@Entity
@Table(name="device", uniqueConstraints = { @UniqueConstraint(columnNames = "reference")})
public class Device {
	@Id	
	private String reference;
	private String name;
	private Float longitude;
	private Float latitude;
	
	@OneToMany(mappedBy="device",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserDevices> list_user_devices;
	
	@ManyToOne
    @JoinColumn(name = "idSpace",insertable=false,updatable=false)
    private Space space;
	private Long idSpace;
	
	@ManyToOne
    @JoinColumn(name = "idConstraint",insertable=false,updatable=false)
    private Constraint_CO2 constraint_co2;
	private Long idConstraint;
	
	@OneToMany(mappedBy="device",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Rt_CO2> list_rt_CO2;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
	@JsonBackReference(value="DeviceSpace")
	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}


	public Device() {}
	public Device(String reference, String name, Float longitude, Float latitude, Space space,Constraint_CO2 constraint_co2) {
		this.reference = reference;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.space = space;
		this.constraint_co2 = constraint_co2;
	}

	public Long getIdSpace() {
		return idSpace;
	}

	public void setIdSpace(Long idSpace) {
		this.idSpace = idSpace;
	}

	@JsonBackReference(value="deviconstraint")
	public Constraint_CO2 getConstraint_co2() {
		return constraint_co2;
	}

	public void setConstraint_co2(Constraint_CO2 constraint_co2) {
		this.constraint_co2 = constraint_co2;
	}

	public Long getIdConstraint() {
		return idConstraint;
	}

	public void setIdConstraint(Long idConstraint) {
		this.idConstraint = idConstraint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonManagedReference(value="DeviceRT")
	public List<Rt_CO2> getList_rt_CO2() {
		return list_rt_CO2;
	}

	public void setList_rt_CO2(List<Rt_CO2> list_rt_CO2) {
		this.list_rt_CO2 = list_rt_CO2;
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
	
	@JsonManagedReference(value="DevicesUser")
	public List<UserDevices> getList_user_devices() {
		return list_user_devices;
	}

	public void setList_user_devices(List<UserDevices> list_user_devices) {
		this.list_user_devices = list_user_devices;
	}
	
	
	
	
}
