/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: DeptController.java
 * Author: Sanero.
 * Created date: Mar 30, 2019
 * Created time: 8:08:13 PM
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

import dev.sanero.entity.Dept;
import dev.sanero.service.DeptService;

/*
 * @author Sanero.
 * Created date: Mar 30, 2019
 * Created time: 8:08:13 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/dept")
public class DeptController {
  @Autowired
  DeptService deptService;

  @GetMapping()
  public ResponseEntity<List<Dept>> findAll() {
    return new ResponseEntity<List<Dept>>(deptService.findAll(), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    if(deptService.delete(id))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK); 
  }

  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody Dept dept) {
    if(deptService.save(dept))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK); 
  }
}
