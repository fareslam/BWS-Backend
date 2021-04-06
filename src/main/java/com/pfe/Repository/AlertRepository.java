package com.pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.pfe.Entity.Alert_C02;
import com.pfe.Entity.Constraint_CO2;



@Repository
public interface AlertRepository extends JpaRepository<Alert_C02,Long>{

}
