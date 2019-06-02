/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: ServiceControlle.java
 * Author: Sanero.
 * Created date: May 11, 2019
 * Created time: 2:08:42 PM
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

import dev.sanero.entity.Employee;
import dev.sanero.entity.Service;
import dev.sanero.entity.ServiceType;
import dev.sanero.service.ServiceService;

/*
 * @author Sanero.
 * Created date: May 11, 2019
 * Created time: 2:08:42 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/service")
public class ServiceController {
  @Autowired
  ServiceService serviceService;

  @GetMapping("/type")
  public ResponseEntity<List<ServiceType>> findAllType() {
    return new ResponseEntity<List<ServiceType>>(serviceService.findAllType(),
        HttpStatus.OK);
  }
  
  @GetMapping("/type-fixed")
  public ResponseEntity<List<ServiceType>> findAllTypeFixed() {
    return new ResponseEntity<List<ServiceType>>(serviceService.findAllTypeFixed(),
        HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<List<Service>> findAll() {
    return new ResponseEntity<List<Service>>(serviceService.findAll(),
        HttpStatus.OK);
  }
  
  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody Service s) {
    boolean  result = serviceService.save(s);
    if(result)
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK); 
  }
  
  @PostMapping("/save-many")
  public ResponseEntity<String> save(@RequestBody List<Service> lst) {
    boolean  result = serviceService.saveService(lst);
    if(result)
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK); 
  }
  
  @PostMapping("/save-type")
  public ResponseEntity<String> saveType(@RequestBody ServiceType t) {
    boolean  result = serviceService.saveType(t);
    if(result)
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK); 
  }
  
  @DeleteMapping("/delete-type/{id}")
  public ResponseEntity<String> deleteType(@PathVariable int id) {
    boolean  result = serviceService.deleteType(id);
    if(result)
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK); 
  }
  
  @GetMapping("/remind/{id}")
  public ResponseEntity<String> remind(@PathVariable int id) {
    Service s = serviceService.findById(id);
    if(serviceService.remind(s))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK); 
  }
  
  @GetMapping("/paid-by-month-type/{month}/{type}")
  public ResponseEntity<List<Object>> paid(@PathVariable String month, @PathVariable int type) {
    return new ResponseEntity<List<Object>>(serviceService.paidByMonthAndType(month, type), HttpStatus.OK);
  }
  

  @GetMapping("/price-paid-by-month-type/{month}/{type}")
  public ResponseEntity<List<Object>> paid2(@PathVariable String month, @PathVariable int type) {
    return new ResponseEntity<List<Object>>(serviceService.pricePaidByMonthAndType(month, type), HttpStatus.OK);
  }
  
  @GetMapping("/paid-by-month/{month}/{paid}")
  public ResponseEntity<List<Object>> paid1(@PathVariable String month, @PathVariable int paid) {
    return new ResponseEntity<List<Object>>(serviceService.paidByMonth(month, paid), HttpStatus.OK);
  }
  
  @GetMapping("/price-paid-by-month/{month}/{paid}")
  public ResponseEntity<List<Object>> paid3(@PathVariable String month, @PathVariable int paid) {
    return new ResponseEntity<List<Object>>(serviceService.pricePaidByMonth(month, paid), HttpStatus.OK);
  }
  
  @PostMapping("/generate")
  public ResponseEntity<String> generate(@RequestBody Employee emp) {
    String result = serviceService.generateServiceFixed(emp);
    return new ResponseEntity<String>(result, HttpStatus.OK); 
  }
  
  @PostMapping("/notify-all")
  public ResponseEntity<String> notifyAll(@RequestBody Employee emp) {
    if(serviceService.notifyAllRoom(emp.getId(), emp.getName()))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);  
  }
  
  @GetMapping("/export-statistic")
  public ResponseEntity<String> statistic() {
    String result = serviceService.exportStatistic()? "Ok" : "Not ok";
    return new ResponseEntity<String>(result, HttpStatus.OK); 
  }
}
