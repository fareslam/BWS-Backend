package com.pfe.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pfe.Entity.SubUserSpace.SubUser_Space;



@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="sub_user", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "cin"),
		@UniqueConstraint(columnNames = "tel")
		})

public class SubUser {
	@Id
	private long cin;
	private String username;
	public String imageurl;
	 @NotBlank
	private String password;
	private String email;
	@JsonFormat(pattern= "yyyy-MM-dd")
	private Date dateBirth;
	private String name;
	private String surname;
	private long tel;
	
	@ManyToOne
    @JoinColumn(name = "cinu",insertable=false,updatable=false)
    private User user;
	private Long cinu;
	
	@OneToMany(mappedBy="sub_user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<SubUser_Space> list_sub_user_spaces;
 	 	
	@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public long getCin() {
		return cin;
	}
	
	
	public String getImageurl() {
		return imageurl;
	}


	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}


	@JsonBackReference(value="uuu")
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Long getCinu() {
		return cinu;
	}



	public void setCinu(Long cinu) {
		this.cinu = cinu;
	}



	public SubUser() {}
	
	
	public SubUser(long cin, String username, String password, String email, Date dateBirth, String name, String surname,
			long tel) {
	
		this.cin = cin;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateBirth = dateBirth;
		this.name = name;
		this.surname = surname;
		this.tel = tel;
	
	}


	public SubUser(long cin, String username, String password, String email, Date dateBirth, String name, String surname,
			long tel,User user) {
	
		this.cin = cin;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateBirth = dateBirth;
		this.name = name;
		this.surname = surname;
		this.tel = tel;
		this.user=user;
	
	}

	public SubUser(long cin, String username, String password, String email, Date dateBirth, String name, String surname,
			long tel,long cinu) {
	
		this.cin = cin;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateBirth = dateBirth;
		this.name = name;
		this.surname = surname;
		this.tel = tel;
		this.cinu=cinu;
	
	}

	
	
	
	public SubUser(long cin, String username, String imageurl, @NotBlank String password, String email, Date dateBirth,
			String name, String surname, long tel, User user) {
		super();
		this.cin = cin;
		this.username = username;
		this.imageurl = imageurl;
		this.password = password;
		this.email = email;
		this.dateBirth = dateBirth;
		this.name = name;
		this.surname = surname;
		this.tel = tel;
		this.user = user;
	}


	@JsonManagedReference(value="UserSpace")
	public List<SubUser_Space> getList_sub_user_spaces() {
		return list_sub_user_spaces;
	}



	public void setList_sub_user_spaces(List<SubUser_Space> list_sub_user_spaces) {
		this.list_sub_user_spaces = list_sub_user_spaces;
	}

	 @PreRemove
	 public void removr() {
	     this.getRoles().clear();
	 }
		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}


	public void setCin(long cin) {
		this.cin = cin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public long getTel() {
		return tel;
	}
	public void setTel(long tel) {
		this.tel = tel;
	}
	
	

}
