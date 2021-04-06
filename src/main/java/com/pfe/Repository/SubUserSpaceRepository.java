package com.pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.SubUserSpace.SUS_key;
import com.pfe.Entity.SubUserSpace.SubUser_Space;

@Repository
public interface SubUserSpaceRepository  extends JpaRepository <SubUser_Space,SUS_key>{

}
