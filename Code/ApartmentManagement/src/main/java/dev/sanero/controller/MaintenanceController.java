/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: MaintenanceController.java
 * Author: Sanero.
 * Created date: May 2, 2019
 * Created time: 10:07:09 PM
 */

package dev.sanero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sanero.entity.Maintenance;
import dev.sanero.service.MaintenanceService;

/*
 * @author Sanero.
 * Created date: May 2, 2019
 * Created time: 10:07:09 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {
  @Autowired
  MaintenanceService service;

  @GetMapping("/group/{id}")
  public ResponseEntity<List<Maintenance>> findAllGroup(@PathVariable int id) {
    return new ResponseEntity<List<Maintenance>>(service.findByGroupId(id),
        HttpStatus.OK);
  }

  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody Maintenance m) {
    if (service.save(m))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }
  
  @GetMapping("/room/{id}")
  public ResponseEntity<List<Maintenance>> findByRoom(@PathVariable int id) {
    return new ResponseEntity<List<Maintenance>>(service.findByRoom(id),
        HttpStatus.OK);
  }
}
