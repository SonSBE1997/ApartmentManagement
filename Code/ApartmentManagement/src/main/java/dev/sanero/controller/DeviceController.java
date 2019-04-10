/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: DeviceController.java
 * Author: Sanero.
 * Created date: Apr 10, 2019
 * Created time: 11:03:25 PM
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

import dev.sanero.entity.Device;
import dev.sanero.entity.DeviceGroup;
import dev.sanero.entity.DeviceType;
import dev.sanero.service.DeviceService;

/*
 * @author Sanero.
 * Created date: Apr 10, 2019
 * Created time: 11:03:25 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/device")
public class DeviceController {
  @Autowired
  DeviceService service;
  
  @GetMapping("/type")
  public ResponseEntity<List<DeviceType>> findAllType(){
    return new ResponseEntity<List<DeviceType>>(service.findAllType(), HttpStatus.OK);
  }
  
  @GetMapping("/group")
  public ResponseEntity<List<DeviceGroup>> findAllGroup(){
    return new ResponseEntity<List<DeviceGroup>>(service.findAllGroup(), HttpStatus.OK);
  }
  
  @GetMapping
  public ResponseEntity<List<Device>> findAll(){
    return new ResponseEntity<List<Device>>(service.findAll(), HttpStatus.OK);
  }
  
  @GetMapping("/group/{id}")
  public ResponseEntity<List<Device>> findAllByGroup(@PathVariable int id){
    return new ResponseEntity<List<Device>>(service.findAllByGroup(id), HttpStatus.OK);
  }
  
  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody Device d) {
    if (service.save(d))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }
}
