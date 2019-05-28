/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: UserService.java
 * Author: Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:24:26 PM
 */

package dev.sanero.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.sanero.entity.HouseHold;
import dev.sanero.entity.User;
import dev.sanero.repository.UserRepository;
import dev.sanero.utils.ResultList;

/*
 * @author Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:24:26 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class UserService {
  @Autowired
  UserRepository repository;
  
  @Autowired 
  HouseholdService householdService;
  
  public ResultList findByPage(Pageable pageable) {
    try {
      ResultList result = new ResultList();
      result.setObj(repository.filterByStatus(false, false, pageable));
      result.setSize(repository.filterByStatusCount(false, false));
      return result;
    } catch (Exception e) {
      return null;
    }
  }
  
  public ResultList searchByPage(String searchStr, Pageable page) {
    try {
      searchStr = "%" + searchStr + "%";
      ResultList result = new ResultList();
      result.setObj(repository.searchByPage(searchStr, page));
      result.setSize(repository.searchCount(searchStr));
      return result;
    } catch (Exception e) {
      return null;
    }
  }

  public List<User> findAll() {
    try {
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }

  public boolean deleteById(int id) {
    try {
      Optional<User> op = repository.findById(id);
      if (op.isPresent()) {
        User u = op.get();
        u.setDisable(true);
        repository.save(u);
        return true;
      }
      return false;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public boolean save(User u) {
    try {
      boolean isNew = true;
      if (u.getId() != 0) {
        isNew = false;
        if (u.getHousehold() == null) {
          User origin = repository.findById(u.getId()).get();
          u.setHousehold(origin.getHousehold());
        }
      }
      u = repository.save(u);
      
      if (isNew && u.isHead()) {
        HouseHold h = u.getHousehold();
        int userId = h.getUserId();
        User user = findById(userId);
        user.setHead(false);
        repository.save(user);
        h.setUserId(u.getId());
        householdService.save(h);
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public ResultList filterByPage(Pageable pageable, Integer buildingId,
      Integer floorId, Integer roomId, String status) {
    try {
       List<User> list = null;
       long size = 0;
       if ("-1".equals(status)) {
         if (buildingId != 0 && floorId == 0) {
           list = repository.filterByBuilding(buildingId, pageable);
           size = repository.filterByBuildingCount(buildingId);
         } else if (roomId == 0 && floorId != 0) {
           list = repository.filterByFloor(floorId, pageable);
           size = repository.filterByFloorCount(floorId);
         } else if (roomId != 0) {
           list = repository.filterByRoom(roomId, pageable);
           size = repository.filterByRoomCount(roomId);
         }
       } else {
         boolean disable = false;
         boolean leave = false;
         if("0".equals(status)) {
           disable = false;
           leave = false;
         } else if("1".equals(status)) {
           disable = true;
           leave = true;
         } else if("2".equals(status)) {
           disable = true;
           leave = false;
         }
         
         if (buildingId != 0 && floorId == 0) {
           list = repository.filterByBuildingAndStatus(buildingId, disable, leave, pageable);
           size = repository.filterByBuildingAndStatusCount(buildingId, disable, leave);
         } else if (roomId == 0 && floorId != 0) {
           list = repository.filterByFloorAndStatus(floorId, disable, leave, pageable);
           size = repository.filterByFloorAndStatusCount(floorId, disable, leave);
         } else if (roomId != 0) {
           list = repository.filterByRoomAndStatus(roomId, disable, leave, pageable);
           size = repository.filterByRoomAndStatusCount(roomId, disable, leave);
         } else {
           list = repository.filterByStatus(disable, leave, pageable);
           size = repository.filterByStatusCount(disable, leave);
         }
       }
       
       if (list == null) {
         list = new ArrayList<>();
       }
       ResultList result = new ResultList();
       result.setObj(list);
       result.setSize(size);
      return result;
    } catch (Exception e) {
      return null;
    }
  }
  
  public List<User> leaveToDay() {
    try {
      Date date = new Date(new java.util.Date().getTime());
      return repository.leaveToday(date);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Author: Sanero.
   * Created date: May 18, 2019
   * Created time: 9:31:25 PM
   * Description: TODO - .
   * @return
   */
  public User findById(int id) {
    try {
      return repository.findById(id).get();
    } catch (Exception e) {
      return null;
    }
  }
}
