package com.pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.Rt_CO2;
import com.pfe.Entity.SubUser;


@Repository
public interface RtRepository extends JpaRepository <Rt_CO2,Long>{

}
