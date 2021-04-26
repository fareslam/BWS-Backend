package com.pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.SubUserSpace.SUS_key;
import com.pfe.Entity.SubUserSpace.SubUser_Space;

@Repository
public interface SubUserSpaceRepository  extends JpaRepository <SubUser_Space,SUS_key>{

  	@Query("select s FROM SubUser_Space s,SubUser su,User u "
			+ "WHERE s.sus_key.cin=su.cin AND su.cinu=u.cinu"
			+ " AND u.cinu =:cinu AND s.sus_key.cin =:cin AND s.sus_key.idSpace =:idSpace")
		public SubUser_Space SubUserSpaceByUser(@Param("cinu") Long cinu,@Param("cin") Long cin,@Param("idSpace") Long idSpace);
}
