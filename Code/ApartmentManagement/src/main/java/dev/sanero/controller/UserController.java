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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.sanero.entity.User;
import dev.sanero.service.UserService;
import dev.sanero.utils.ResultList;

/*
 * @author Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:25:45 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping()
  public ResponseEntity<List<User>> findAll() {
    return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/paginator")
  public ResponseEntity<ResultList> findByPage(
      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
      @RequestParam(name = "searchStr", required = false, defaultValue = "") String searchStr) {
    Pageable pageable = PageRequest.of(page, pageSize);
    if (searchStr == "")
      return new ResponseEntity<ResultList>(userService.findByPage(pageable),
          HttpStatus.OK);
    return new ResponseEntity<ResultList>(
        userService.searchByPage(searchStr, pageable), HttpStatus.OK);
  }

  @GetMapping("/filter")
  public ResponseEntity<ResultList> filterByPage(
      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
      @RequestParam(name = "buildingId", required = false, defaultValue = "0") Integer buildingId,
      @RequestParam(name = "floorId", required = false, defaultValue = "0") Integer floorId,
      @RequestParam(name = "roomId", required = false, defaultValue = "0") Integer roomId,
      @RequestParam(name = "status", required = false, defaultValue = "-1") String status) {
    Pageable pageable = PageRequest.of(page, pageSize);
    return new ResponseEntity<ResultList>(userService.filterByPage(pageable, buildingId, floorId, roomId, status),
        HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    if (userService.deleteById(id))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }

  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody User user) {
    if (userService.save(user))
      return new ResponseEntity<String>("OK", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }
}
