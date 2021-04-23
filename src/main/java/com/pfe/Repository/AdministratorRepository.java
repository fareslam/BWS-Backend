package com.pfe.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.pfe.Entity.Administrator;
import com.pfe.Entity.Device;


@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,Long>{

	
	@Query("select a FROM Administrator a,SubUser u WHERE a.cin_admin=u.cin AND a.cin_admin =:cin") 
	public Optional<Administrator> adminBySubUser(@Param("cin") Long cin);
	
	
	@Query("select d FROM Device d WHERE d.idConstraint IS NULL") 
	public List<Device> devicesbyIdConstraint();
}
