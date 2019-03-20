/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: BuildingService.java
 * Author: Sanero.
 * Created date: Mar 13, 2019
 * Created time: 8:53:41 PM
 */

package dev.sanero.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.Building;
import dev.sanero.repository.BuildingRepository;

/*
 * @author Sanero.
 * Created date: Mar 13, 2019
 * Created time: 8:53:41 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class BuildingService {
  @Autowired
  BuildingRepository repository;
  
  public List<Building> findAll(){
    try {
      return repository.findAllBuildingsByDisable(false);
    } catch (Exception e) {
      return null;
    }
  }
  
  public boolean deleteById(int id) {
    try {
      Optional<Building> op = repository.findById(id);
      if(op.isPresent()) {
        Building b = op.get();
        b.setDisable(true);
        repository.save(b);
        return true;
      }
      return false;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }
  
  public boolean save(List<Building> building) {
    try {
      for (Building b : building) {
        repository.save(b);
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
