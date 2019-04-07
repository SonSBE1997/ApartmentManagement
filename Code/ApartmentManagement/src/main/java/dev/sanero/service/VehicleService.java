/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: VehicleService.java
 * Author: Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:27:03 PM
 */

package dev.sanero.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.Vehicle;
import dev.sanero.repository.VehicleRepository;

/*
 * @author Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:27:03 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class VehicleService {
  @Autowired
  VehicleRepository repository;
  
  public List<Vehicle> findAll() {
    try {
      return repository.findAllVehiclesByDisable(false);
    } catch (Exception e) {
      return null;
    }
  }
  
  public List<Vehicle> findAllVehicleByUser(int userId) {
    try {
      return repository.findByUserId(userId);
    } catch (Exception e) {
      return null;
    }
  }
  
  public Vehicle findById(String id) {
    try {
      return repository.findById(id).get();
    } catch (Exception e) {
      return null;
    }
  }
  
  public Vehicle save(Vehicle c) {
    try {
      return repository.save(c);
    } catch (Exception e) {
      return null;
    }
  }
  
  public boolean delete(String id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
