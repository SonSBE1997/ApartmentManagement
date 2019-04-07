/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: VehicleTypeService.java
 * Author: Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:26:47 PM
 */

package dev.sanero.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.VehicleType;
import dev.sanero.repository.VehicleTypeRepository;

/*
 * @author Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:26:47 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class VehicleTypeService {
  @Autowired
  VehicleTypeRepository repository;
  
  public List<VehicleType> findAll() {
    try {
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }
}
