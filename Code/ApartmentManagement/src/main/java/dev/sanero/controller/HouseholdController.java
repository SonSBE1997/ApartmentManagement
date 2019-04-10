/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: HouseholdController.java
 * Author: Sanero.
 * Created date: Mar 17, 2019
 * Created time: 3:26:21 PM
 */

package dev.sanero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sanero.entity.HouseHold;
import dev.sanero.service.HouseholdService;

/*
 * @author Sanero.
 * Created date: Mar 17, 2019
 * Created time: 3:26:21 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/household")
public class HouseholdController {
  @Autowired
  HouseholdService householdService;

  @GetMapping(value = "/filter/{roomId}/{comeDate}/{leaveDate}")
  public ResponseEntity<List<HouseHold>> findAllByRoomIdAndComeDateAndLeaveDate(
      @PathVariable int roomId, @PathVariable String comeDate,
      @PathVariable String leaveDate) {
    return new ResponseEntity<List<HouseHold>>(householdService
        .findAllByRoomIdAndComeDateAndLeaveDate(roomId, comeDate, leaveDate),
        HttpStatus.OK);
  }

  @GetMapping(value = "/live")
  public ResponseEntity<List<HouseHold>> findAllHouseholdIsLive() {
    return new ResponseEntity<List<HouseHold>>(
        householdService.findAllHouseholdIslive(), HttpStatus.OK);
  }
  
  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody HouseHold h) {
    if (householdService.save(h))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }
  
  @PutMapping("/update")
  public ResponseEntity<String> update(@RequestBody HouseHold h) {
    if (householdService.update(h))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }
  
  @GetMapping(value = "/come-today")
  public ResponseEntity<List<HouseHold>> findHouseHoldComeToDay() {
    return new ResponseEntity<List<HouseHold>>(
        householdService.findHouseHoldComeToDay(), HttpStatus.OK);
  }
  
  @GetMapping(value = "/{id}")
  public ResponseEntity<HouseHold> findById(@PathVariable int id) {
    return new ResponseEntity<HouseHold>(
        householdService.findById(id), HttpStatus.OK);
  }
  
  @PostMapping("/cancel")
  public ResponseEntity<String> cancelCome(@RequestBody HouseHold h) {
    if (householdService.cancel(h.getId()))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }
  
  @PostMapping("/register-leave")
  public ResponseEntity<String> registerLeave(@RequestBody HouseHold h) {
    if (householdService.registerLeave(h.getId(), h.getLeaveDate()))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }
}
