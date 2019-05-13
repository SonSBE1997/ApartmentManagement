/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: MaintenananceRepository.java
 * Author: Sanero.
 * Created date: May 2, 2019
 * Created time: 10:00:43 PM
 */

package dev.sanero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Maintenance;

/*
 * @author Sanero.
 * Created date: May 2, 2019
 * Created time: 10:00:43 PM
 * Description: TODO - 
 */
@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer>{
  
  @Query("select m from maintenance m WHERE m.deviceGroup.id = ?1 order by m.maintenanceDate desc")
  public List<Maintenance> findByGroupId(int groupId);
  
  @Query("select m from maintenance m WHERE m.deviceGroup.id = 5 and m.description = ?1 order by m.maintenanceDate desc")
  public List<Maintenance> findByRoom(String room);
}
