package com.pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.UserDevices.UDKey;
import com.pfe.Entity.UserDevices.UserDevices;

@Repository
public interface UserDevicesRepository extends JpaRepository<UserDevices,UDKey>{

}
