package com.pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.SubUser;
import com.pfe.Entity.ClientArea.ClientArea;
import com.pfe.Entity.ClientArea.UA_key;
import com.pfe.Entity.UserDevices.UserDevices;

@Repository
public interface ClientAreaRepository  extends JpaRepository <ClientArea,UA_key>{

	@Query("select ca FROM ClientArea ca WHERE ca.ua_key.cinu=:cinu AND ca.ua_key.idArea =:idArea") 
	public ClientArea ClientAreaByCI(@Param("cinu") Long cinu,@Param("idArea") Long idArea);
}
