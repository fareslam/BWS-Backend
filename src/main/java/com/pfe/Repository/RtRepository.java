package com.pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.SubUser;
import com.pfe.Entity.RealTime.Rt_CO2;


@Repository
public interface RtRepository extends JpaRepository <Rt_CO2,String>{

}
