/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: BuildingRepository.java
 * Author: Sanero.
 * Created date: Mar 13, 2019
 * Created time: 8:51:53 PM
 */

package dev.sanero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Building;

/*
 * @author Sanero.
 * Created date: Mar 13, 2019
 * Created time: 8:51:53 PM
 * Description: TODO - 
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
  public List<Building> findAllBuildingsByDisable(boolean disable);
  
  @Query(value ="select * from building where name like ?1 and disable != 1", nativeQuery = true)
  public List<Building> findByName(String name);
}
