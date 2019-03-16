/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: BuildingController.java
 * Author: Sanero.
 * Created date: Mar 13, 2019
 * Created time: 8:59:18 PM
 */

package dev.sanero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sanero.entity.Building;
import dev.sanero.service.BuildingService;

/*
 * @author Sanero.
 * Created date: Mar 13, 2019
 * Created time: 8:59:18 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/building")
public class BuildingController {
  @Autowired
  BuildingService buildingService;
  
  @GetMapping()
  public ResponseEntity<List<Building>> findAll(){
    return new ResponseEntity<List<Building>>(buildingService.findAll(), HttpStatus.OK) ;
  }
}
