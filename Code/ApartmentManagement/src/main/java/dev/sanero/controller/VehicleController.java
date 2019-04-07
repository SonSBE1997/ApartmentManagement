/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: VehicleController.java
 * Author: Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:46:33 PM
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

import dev.sanero.entity.Vehicle;
import dev.sanero.entity.VehicleType;
import dev.sanero.service.VehicleService;
import dev.sanero.service.VehicleTypeService;

/*
 * @author Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:46:33 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
  @Autowired
  VehicleService vehicleService;
  @Autowired
  VehicleTypeService vehicleTypeService;

  @GetMapping("/type")
  public ResponseEntity<List<VehicleType>> findAllvehicleType() {
    return new ResponseEntity<List<VehicleType>>(vehicleTypeService.findAll(),
        HttpStatus.OK);
  }

  @GetMapping("/vehicle/{userId}")
  public ResponseEntity<List<Vehicle>> findByUserId(@PathVariable int userId) {
    return new ResponseEntity<List<Vehicle>>(
        vehicleService.findAllVehicleByUser(userId), HttpStatus.OK);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Vehicle> findById(@PathVariable String id) {
    return new ResponseEntity<Vehicle>(vehicleService.findById(id), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deletevehicle(@PathVariable String id) {
    if (vehicleService.delete(id))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }

  @PostMapping("/save")
  public ResponseEntity<Vehicle> save(@RequestBody Vehicle vehicle) {
    return new ResponseEntity<Vehicle>(vehicleService.save(vehicle),
        HttpStatus.OK);
  }
}
