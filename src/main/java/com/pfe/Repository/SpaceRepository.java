package com.pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.Space;
import com.pfe.Entity.SubUser;

@Repository
public interface SpaceRepository  extends JpaRepository <Space,Long>{

}
