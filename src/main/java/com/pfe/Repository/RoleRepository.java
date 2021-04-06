package com.pfe.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.ERole;
import com.pfe.Entity.Role;



@Repository
public interface RoleRepository  extends JpaRepository<Role,Long>{
	Optional<Role> findByName(ERole name);
}
