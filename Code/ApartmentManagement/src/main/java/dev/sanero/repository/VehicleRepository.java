/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: VehicleRepository.java
 * Author: Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:23:03 PM
 */

package dev.sanero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Vehicle;

/*
 * @author Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:23:03 PM
 * Description: TODO - 
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String>{
  public List<Vehicle> findAllVehiclesByDisable(boolean disable);
  
  @Query("select v from verhicle v WHERE v.user.id = ?1")
  public List<Vehicle> findByUserId(int userId);
}
