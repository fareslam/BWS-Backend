package com.pfe.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.Entity.History.HistKey;
import com.pfe.Entity.History.History_CO2;
import com.pfe.Entity.RealTime.Rt_CO2;

@Repository
public interface HistoryRepository extends JpaRepository <History_CO2,Date>{

}
