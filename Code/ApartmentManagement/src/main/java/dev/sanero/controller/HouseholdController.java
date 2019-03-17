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
    return new ResponseEntity<List<HouseHold>>(
        householdService.findAllByRoomIdAndComeDateAndLeaveDate(roomId,
            comeDate, leaveDate),
        HttpStatus.OK);
  }
}
