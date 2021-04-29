package com.pfe.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.pfe.Entity.Constraint_CO2;
import com.pfe.Entity.Alert.Alert_C02;



@Repository
public interface AlertRepository extends JpaRepository<Alert_C02,Date>{

}
