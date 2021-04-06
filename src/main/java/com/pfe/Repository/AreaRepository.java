package com.pfe.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.Area;
import com.pfe.Entity.Device;


@Repository
public interface AreaRepository  extends JpaRepository <Area,Long>{

	Optional<Area> findByIdArea(Long idArea);

}
