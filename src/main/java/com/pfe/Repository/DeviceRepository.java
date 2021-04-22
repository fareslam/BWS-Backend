package com.pfe.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.pfe.Entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device,String>{

	Boolean existsByReference(String reference);

	Device findByReference(String reference);
}
