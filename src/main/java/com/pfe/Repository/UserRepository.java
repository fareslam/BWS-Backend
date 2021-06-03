package com.pfe.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;


import com.pfe.Entity.Administrator;
import com.pfe.Entity.Device;
import com.pfe.Entity.Role;
import com.pfe.Entity.Space;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;
import com.pfe.Entity.Alert.Alert_C02;
import com.pfe.Entity.History.History_CO2;
import com.pfe.Entity.SubUserSpace.SubUser_Space;
import com.pfe.Entity.UserDevices.UserDevices;


@Repository
public interface UserRepository extends JpaRepository <User,Long>{


	Optional<User> findByCinu(Long cinu);
	boolean existsByCinu(Long cinu);

	
	@Query("select u FROM User u,SubUser su WHERE u.cinu=su.cin AND u.cinu =:cinu") 
	public Optional<User> userBySubUser(@Param("cinu") Long cinu);
	
	@Query("select su FROM SubUser su,User u "
			+ "WHERE su.cinu=u.cinu"
			+ " AND u.cinu =:cinu") 	
	public List<SubUser> listSubUserByUser(@Param("cinu") Long cinu);
	
	
	@Query("select s.longitude,s.latitude FROM Space s,User u,Device d,UserDevices ud "
			+ "WHERE u.cinu=ud.udk.cinu AND ud.udk.reference=d.reference "
			+ "AND d.idSpace=s.idSpace "
			+ "AND u.cinu =:cinu") 
	public List<?> listSpacesPerUserDevice(@Param("cinu") Long cinu);
	
	
	@Query("select s FROM Space s,User u,Device d,UserDevices ud "
			+ "WHERE u.cinu=ud.udk.cinu AND ud.udk.reference=d.reference "
			+ "AND d.idSpace=s.idSpace "
			+ "AND u.cinu =:cinu") 
	public List<Space> ScndlistSpacesPerUserDevice(@Param("cinu") Long cinu);
	
	
	
	@Query("select s FROM SubUser_Space s,SubUser su,User u "
			+ "WHERE s.sus_key.cin=su.cin AND su.cinu=u.cinu"
			+ " AND u.cinu =:cinu") 	
	public List<SubUser_Space> listSubUsersInSpaceByUser(@Param("cinu") Long cinu);
	
	
	
			
	
	@Query("select a.geojson FROM Area a,User u,ClientArea ca "
			+ "WHERE u.cinu=ca.ua_key.cinu AND ca.ua_key.idArea=a.idArea "
			+ "AND u.cinu =:cinu") 
	public List<?> listAreasPerUser(@Param("cinu") Long cinu);
	
	
	
@Query("select d FROM Device d, UserDevices ud, User u "
	+ "WHERE ud.udk.cinu =u.cinu "
	+ "AND ud.udk.reference=d.reference "
	+ "AND u.cinu =:cinu")
public List<Device> listDevicesPerUser(@Param("cinu") Long cinu);

/*
@Query("select rt.value_co2 FROM Rt_CO2 rt, UserDevices ud, User u,Device d "
		+ "WHERE ud.udk.cinu =u.cinu "
		+ "AND ud.udk.reference=d.reference AND rt.reference=d.reference "
		+ "AND u.cinu =:cinu AND rt.reference=:reference")
	public List<Float> valueRTperDevPerUser(@Param("cinu") Long cinu,@Param("reference") String reference);*/


@Query("select a FROM Alert_C02 a,UserDevices ud,User u "
		+ "WHERE ud.udk.cinu =u.cinu "
		+ "AND ud.udk.reference=a.reference"
		+ " AND u.cinu =:cinu AND a.reference=:reference") 	
public List<Alert_C02> reportAlertByRef(@Param("cinu") Long cinu,@Param("reference") String reference);


@Query("select h FROM History_CO2 h,UserDevices ud,User u "
		+ "WHERE ud.udk.cinu =u.cinu "
		+ "AND ud.udk.reference=h.reference"
		+ " AND u.cinu =:cinu AND h.reference=:reference") 	
public List<History_CO2> reportHistoryByRef(@Param("cinu") Long cinu,@Param("reference") String reference);

@Query("select DISTINCT(h.value),h.date,h.reference,a.message FROM History_CO2 h,Alert_C02 a,UserDevices ud,User u "
		+ "WHERE ud.udk.cinu =u.cinu "
		+ "AND ud.udk.reference=h.reference AND h.reference=a.reference AND h.date=a.date"
		+ " AND u.cinu =:cinu AND h.reference=:reference") 	
public List<?> reportHistoryAlertByRef(@Param("cinu") Long cinu,@Param("reference") String reference);



@Query("select a FROM Alert_C02 a,UserDevices ud,User u "
		+ "WHERE ud.udk.cinu =u.cinu "
		+ "AND ud.udk.reference=a.reference"
		+ " AND u.cinu =:cinu AND a.date <= :date") 	
public List<Alert_C02> reportAlertByDate(@Param("cinu") Long cinu, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  @Param("date") Date date);

}
