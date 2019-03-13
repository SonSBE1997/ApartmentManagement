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
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }
}
