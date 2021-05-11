package com.pfe.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.Device;
import com.pfe.Entity.Space;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;
import com.pfe.Entity.Alert.Alert_C02;
import com.pfe.Entity.History.History_CO2;
import com.pfe.Entity.SubUserSpace.SubUser_Space;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
@Repository
public interface SubUserRepository extends JpaRepository <SubUser,Long>{

	boolean existsByTel(long tel);

	boolean existsByCin(long cin);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	Optional<SubUser> findByCin(Long cin);
	
	Optional<SubUser> findByUsername(String username);
	
	Optional<SubUser> findByEmail(String email);
	

	@Query("select s FROM Space s,SubUser_Space ss,SubUser su,User u "
			+ "WHERE ss.sus_key.cin=su.cin AND s.idSpace=ss.sus_key.idSpace AND su.cinu=u.cinu"
			+ " AND su.cin =:cin") 	
	public List<Space> listSubUsersSpace(@Param("cin") Long cin);
	
	
	
	@Query("select d FROM Device d,UserDevices ud,User u,SubUser su "
			+ "WHERE ud.udk.cinu =u.cinu AND su.cinu=u.cinu "
			+ "AND ud.udk.reference=d.reference"
			+ " AND su.cin =:cin") 	
	public List<Device> listDevices(@Param("cin") Long cin);
	
	
	@Query("select a FROM Alert_C02 a,UserDevices ud,User u,SubUser su "
			+ "WHERE ud.udk.cinu =u.cinu AND su.cinu=u.cinu "
			+ "AND ud.udk.reference=a.reference"
			+ " AND su.cin =:cin AND a.reference=:reference") 	
	public List<Alert_C02> reportAlertByRef(@Param("cin") Long cin,@Param("reference") String reference);
	

@Query("select h FROM History_CO2 h,UserDevices ud,User u,SubUser su "
		+ "WHERE ud.udk.cinu =u.cinu AND su.cinu=u.cinu "
		+ "AND ud.udk.reference=h.reference"
		+ " AND su.cin =:cin AND h.reference=:reference") 	
public List<History_CO2> reportHistoryByRef(@Param("cin") Long cin,@Param("reference") String reference);

@Query("select DISTINCT(h.value),h.date,h.reference,a.message FROM History_CO2 h,Alert_C02 a,UserDevices ud,User u,SubUser su "
		+ "WHERE ud.udk.cinu =u.cinu AND su.cinu=u.cinu "
		+ "AND ud.udk.reference=h.reference AND h.reference=a.reference AND h.date=a.date"
		+ " AND su.cin =:cin AND h.reference=:reference") 	
public List<?> reportHistoryAlertByRef(@Param("cin") Long cin,@Param("reference") String reference);



@Query("select h.value FROM History_CO2 h,Device d,Rt_CO2 rt"
		+ " WHERE h.reference=rt.reference AND rt.reference=d.reference "
		+ "AND d.reference =:reference") 
public List<Float> AllvaluesRT(@Param("reference") String reference);
	
}
