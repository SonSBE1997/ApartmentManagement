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
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }

  public boolean deleteById(int id) {
    try {
      long beforeDelete = repository.count();
      repository.deleteById(id);
      long afterDelete  = repository.count();
      if(beforeDelete > afterDelete) {
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean save(Floor f) {
    try {
      repository.save(f);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
