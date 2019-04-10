/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: FloorService.java
 * Author: Sanero.
 * Created date: Mar 18, 2019
 * Created time: 10:16:56 PM
 */

package dev.sanero.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.Floor;
import dev.sanero.repository.FloorRepository;

/*
 * @author Sanero.
 * Created date: Mar 18, 2019
 * Created time: 10:16:56 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class FloorService {
  @Autowired
  FloorRepository repository;
  
  public List<Floor> findAll() {
    try {
      return repository.findAllFloorsByDisable(false);
    } catch (Exception e) {
      return null;
    }
  }

  public boolean deleteById(int id) {
    try {
      Optional<Floor> op = repository.findById(id);
      if(op.isPresent()) {
        Floor f = op.get();
        f.setDisable(true);
        repository.save(f);
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean save(List<Floor> floors) {
    try {
      for (Floor f : floors) {
        save(f);
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public Floor save(Floor f) {
    try {
      if (f.getId() != 0) {
        Floor origin = repository.findById(f.getId()).get();
        f.setRooms(origin.getRooms());
        f.setBuilding(origin.getBuilding());
      }
      return repository.save(f);
    } catch (Exception e) {
      return null;
    }
  }
  
  public Floor findByNameAndBuilding(String name, int buildingId) {
    List<Floor> floors = repository.findByName(name, buildingId);
    if(floors.size() > 0) {
      return floors.get(0);
    }
    return null;
  }
}
