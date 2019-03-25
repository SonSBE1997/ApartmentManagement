/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: UserController.java
 * Author: Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:25:45 PM
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

import dev.sanero.entity.User;
import dev.sanero.service.UserService;

/*
 * @author Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:25:45 PM
 * Description: TODO - 
 */
@RestController
//@RequestMapping("/api/user")
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;
  
  @GetMapping()
  public ResponseEntity<List<User>> findAll(){
    return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK) ;
  }
  
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id){
    if(userService.deleteById(id))
      return new ResponseEntity<String>("Ok",HttpStatus.OK);
    return new ResponseEntity<String>("Not ok",HttpStatus.OK);
  }
  
  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody User user){
    if(userService.save(user))
      return new ResponseEntity<String>("OK",HttpStatus.OK);
    return new ResponseEntity<String>("Not ok",HttpStatus.OK);
  }
}
