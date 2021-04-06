package com.pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.Area;


@Repository
public interface AreaRepository  extends JpaRepository <Area,Long>{

}
