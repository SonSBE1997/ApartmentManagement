/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: EmployeeControllre.java
 * Author: Sanero.
 * Created date: Mar 30, 2019
 * Created time: 8:08:23 PM
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
import dev.sanero.service.EmployeeService;

/*
 * @author Sanero.
 * Created date: Mar 30, 2019
 * Created time: 8:08:23 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeControllre {
  @Autowired
  EmployeeService empService;

  @GetMapping()
  public ResponseEntity<List<Employee>> findAll() {
    return new ResponseEntity<List<Employee>>(empService.findAll(), HttpStatus.OK);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Employee> findById(@PathVariable int id) {
    return new ResponseEntity<Employee>(empService.findById(id), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    if(empService.delete(id))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);    
  }

  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody Employee emp) {
    int result = empService.save(emp);
    if(result == 2)
      return new ResponseEntity<String>("Duplicate username", HttpStatus.OK);
    if(result == 1)
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK); 
  }
}
