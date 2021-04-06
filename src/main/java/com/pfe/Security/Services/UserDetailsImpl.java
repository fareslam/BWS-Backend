
package com.pfe.Security.Services;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pfe.Entity.SubUser;



public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long cin;

	private String username;
	
	private String name;
	
	private String surname;

	private long tel;
	
	@JsonFormat(pattern= "yyyy-MM-dd")
	private Date dateBirth;
	
	private String email;

	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long cin, String username,String password,String email,Date dt, String nom,String prenom, long tel,
			Collection<? extends GrantedAuthority> authorities) {
		this.cin = cin;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateBirth=dt;

		this.name=nom;
		this.surname=prenom;
		this.tel=tel;


		this.authorities = authorities;
	}



	public static UserDetailsImpl build(SubUser user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getCin(),
				user.getUsername(), 
				user.getPassword(), 
				user.getEmail(),
				user.getDateBirth(),
				user.getName(),
				user.getSurname(),
				user.getTel(),

				authorities);
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getCin() {
		return cin;
	}

	public String getEmail() {
		return email;
	}
	
	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
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



	public Date getDateBirth() {
		return dateBirth;
	}



	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setCin(Long cin) {
		this.cin = cin;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}



	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(cin, user.cin);
	}
}
