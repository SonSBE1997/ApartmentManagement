/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: HouseholdService.java
 * Author: Sanero.
 * Created date: Mar 17, 2019
 * Created time: 3:21:05 PM
 */

package dev.sanero.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.HouseHold;
import dev.sanero.entity.Room;
import dev.sanero.entity.User;
import dev.sanero.repository.HouseholdRepository;
import dev.sanero.repository.UserRepository;

/*
 * @author Sanero.
 * Created date: Mar 17, 2019
 * Created time: 3:21:05 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class HouseholdService {
  @Autowired
  HouseholdRepository repository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  UserService userService;
  @Autowired 
  RoomService roomService;


  public List<HouseHold> findAllByRoomIdAndComeDateAndLeaveDate(int roomId,
      String comeDate, String leaveDate) {
    try {
      return repository.findAllByRoomIdAndComeDateAndLeaveDate(roomId, comeDate,
          leaveDate);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public List<HouseHold> findAllHouseholdIslive() {
    try {
      return repository.findAllHouseholdIsLive();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public HouseHold findById(int id) {
    try {
      return repository.findById(id).get();
    } catch (Exception e) {
      return null;
    }
  }

  public boolean save(HouseHold h) {
    try {
      boolean checkExist = false;
      if(h.getId() != 0) {
        HouseHold origin = findById(h.getId());
        h.setRoom(origin.getRoom());
        h.setUsers(origin.getUsers());
        h.setEmployee(origin.getEmployee());
        checkExist = true;
      }
      h = repository.save(h);
      if (!checkExist) {
        for(User u : h.getUsers()) {
          u.setHousehold(h);
          u = userRepository.save(u);
          h.setUserId(u.getId());
        }
        Room r = h.getRoom();
        r.setHousehold(h.getId());
        String sttus = h.isHire()? "3" : "4";
        r.setStatus(sttus);
        roomService.saveRoom(r);
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean update(HouseHold h) {
    try {
      if(h.getId() != 0) {
        HouseHold origin = findById(h.getId());
        h.setRoom(origin.getRoom());
        h.setUsers(origin.getUsers());
        h.setEmployee(origin.getEmployee());
      }
      repository.saveAndFlush(h);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean cancel(int id) {
    try {
      HouseHold h = repository.findById(id).get();
      h.setLeaveDate(h.getComeDate());
      h.setStatus("4");
      h = repository.save(h);
      Room r = h.getRoom();
      r.setHousehold(0);
      r.setStatus("0");
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean registerLeave(int id, Date leave) {
    try {
      HouseHold h = repository.findById(id).get();
      h.setLeaveDate(leave);
      h.setStatus("3");
      h = repository.save(h);
      
      h.getUsers().forEach(u -> {
        if(u.getLeaveDate() == null) {
          u.setLeaveDate(leave);
          u.setDisable(true);
          userService.save(u);
        }
      });
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean cancelLeave(int id) {
    try {
      HouseHold h = repository.findById(id).get();
      h.setLeaveDate(null);
      h.setStatus("1");
      h = repository.save(h);
      
      h.getUsers().forEach(u -> {
        if(u.getLeaveDate() == null) {
          u.setLeaveDate(null);
          u.setDisable(false);
          userService.save(u);
        }
      });
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  
  public List<HouseHold> findHouseHoldComeToDay() {
    try {
      Date date = new Date(new java.util.Date().getTime());
      return repository.findHouseHoldComeToDay(date);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}
