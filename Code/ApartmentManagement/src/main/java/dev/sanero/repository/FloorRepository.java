/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: FloorRepository.java
 * Author: Sanero.
 * Created date: Mar 18, 2019
 * Created time: 10:16:01 PM
 */

package dev.sanero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Floor;

/*
 * @author Sanero.
 * Created date: Mar 18, 2019
 * Created time: 10:16:01 PM
 * Description: TODO - 
 */
@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer>{
  public List<Floor> findAllFloorsByDisable(boolean disable);
  
  @Query(value ="select * from floor where name like ?1 and building_id = ?2 and disable != 1 ", nativeQuery = true)
  public List<Floor> findByName(String name, int buildingId);
}
