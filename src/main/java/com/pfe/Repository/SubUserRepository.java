package com.pfe.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.Device;
import com.pfe.Entity.SubUser;
import com.pfe.Entity.User;

@Repository
public interface SubUserRepository extends JpaRepository <SubUser,Long>{

	boolean existsByTel(long tel);

	boolean existsByCin(long cin);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	Optional<SubUser> findByCin(Long cin);
	
	Optional<SubUser> findByUsername(String username);
}
