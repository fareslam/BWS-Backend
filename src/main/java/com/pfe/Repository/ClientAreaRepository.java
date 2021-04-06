package com.pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.SubUser;
import com.pfe.Entity.ClientArea.ClientArea;
import com.pfe.Entity.ClientArea.UA_key;

@Repository
public interface ClientAreaRepository  extends JpaRepository <ClientArea,UA_key>{

}
