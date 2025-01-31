/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: RoomController.java
 * Author: Sanero.
 * Created date: Mar 16, 2019
 * Created time: 12:55:00 PM
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sanero.entity.Room;
import dev.sanero.service.RoomService;

/*
 * @author Sanero.
 * Created date: Mar 16, 2019
 * Created time: 12:55:00 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping(value = "/api/room")
public class RoomController {
  @Autowired
  RoomService roomService;

  @GetMapping(value = "/{roomId}")
  public ResponseEntity<Room> findById(@PathVariable int roomId) {
    return new ResponseEntity<Room>(roomService.findById(roomId),
        HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<List<Room>> findAll() {
    return new ResponseEntity<List<Room>>(roomService.findAll(), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    roomService.changeDisale(id);
    return new ResponseEntity<String>("Ok", HttpStatus.OK);
  }

  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody List<Room> r) {
    roomService.save(r);
    return new ResponseEntity<String>("Ok", HttpStatus.OK);
  }
  
  @PutMapping("/update")
  public ResponseEntity<String> update(@RequestBody Room r) {
    if (roomService.update(r))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }
}
