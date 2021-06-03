package com.pfe.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.pfe.Entity.Device;
import com.pfe.Entity.RealTime.Rt_CO2;

@Repository
public interface DeviceRepository extends JpaRepository<Device,String>{

	Boolean existsByReference(String reference);

	Device findByReference(String reference);
	
	@Query("select rt FROM Device d,Rt_CO2 rt "
			+ "WHERE d.reference=rt.reference "
			+ "AND d.reference =:reference")
		public Rt_CO2 DeviceByLastValueRT(@Param("reference") String reference);
}
