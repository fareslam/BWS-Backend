package com.pfe.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.Constraint_CO2;
import com.pfe.Entity.Device;

@Repository
public interface ConstraintRepository extends JpaRepository<Constraint_CO2,Long>{
	Boolean existsByIdConstraint(Long idConstraint);
	Optional<Constraint_CO2> findByIdConstraint(Long idConstraint);

}
