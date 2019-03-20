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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sanero.entity.Floor;
import dev.sanero.service.FloorService;

/*
 * @author Sanero.
 * Created date: Mar 13, 2019
 * Created time: 8:59:18 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/floor")
public class FloorController {
  @Autowired
  FloorService floorService;
  
  @GetMapping()
  public ResponseEntity<List<Floor>> findAll(){
    return new ResponseEntity<List<Floor>>(floorService.findAll(), HttpStatus.OK) ;
  }
  
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id){
    floorService.deleteById(id);
    return new ResponseEntity<String>(HttpStatus.OK);
  }
  
  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody List<Floor> f){
    floorService.save(f);
    return new ResponseEntity<String>(HttpStatus.OK);
  }
}
