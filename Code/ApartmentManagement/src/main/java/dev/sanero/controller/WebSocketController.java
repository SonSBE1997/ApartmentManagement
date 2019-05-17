/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: WebSocketController.java
 * Author: Sanero.
 * Created date: May 15, 2019
 * Created time: 8:30:29 PM
 */

package dev.sanero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dev.sanero.entity.HouseHold;
import dev.sanero.entity.User;
import dev.sanero.service.HouseholdService;
import dev.sanero.service.ServiceService;
import dev.sanero.service.UserService;

/*
 * @author Sanero.
 * Created date: May 15, 2019
 * Created time: 8:30:29 PM
 * Description: TODO - 
 */
@RestController
public class WebSocketController {
  @Autowired
  UserService userService;
  
  @Autowired
  HouseholdService householdService;
  
  @Autowired 
 ServiceService serviceService;
  
  @MessageMapping("/come")
  @SendTo("/topic/come")
  public List<HouseHold> come() throws Exception {
    return householdService.findHouseHoldComeToDay();
  }
  
  @MessageMapping("/leave")
  @SendTo("/topic/leave")
  public List<User> leave() throws Exception {
    return userService.leaveToDay();
  }
  
  @GetMapping("/{month}/{type}")
  public List<Object> paid(@PathVariable String month, @PathVariable int type) {
    return serviceService.paidByMonthAndType("05-2019", 1);
  }
}
