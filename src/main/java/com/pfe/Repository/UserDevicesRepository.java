package com.pfe.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.User;
import com.pfe.Entity.UserDevices.UDKey;
import com.pfe.Entity.UserDevices.UserDevices;

@Repository
public interface UserDevicesRepository extends JpaRepository<UserDevices,UDKey>{
	@Query("select ud FROM UserDevices ud WHERE ud.udk.cinu=:cinu AND ud.udk.reference =:reference") 
	public UserDevices UserDevicesByCR(@Param("cinu") Long cinu,@Param("reference") String reference);
}
