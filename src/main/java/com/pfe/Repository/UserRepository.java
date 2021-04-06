package com.pfe.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.pfe.Entity.Administrator;
import com.pfe.Entity.Device;
import com.pfe.Entity.Role;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;


@Repository
public interface UserRepository extends JpaRepository <User,Long>{



	boolean existsByCinu(Long cinu);

	
	@Query("select u FROM User u,SubUser su WHERE u.cinu=su.cin AND u.cinu =:cinu") 
	public Optional<User> userBySubUser(@Param("cinu") Long cinu);

	Optional<User> findByCinu(Long cinu);
	
	
	
	@Query("select su FROM SubUser su,User u "
			+ "WHERE su.cinu=u.cinu"
			+ " AND u.cinu =:cinu") 	
	public List<SubUser> listSubUserByUser(@Param("cinu") Long cinu);
}
