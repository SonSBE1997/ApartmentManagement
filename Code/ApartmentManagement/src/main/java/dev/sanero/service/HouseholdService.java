/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: HouseholdService.java
 * Author: Sanero.
 * Created date: Mar 17, 2019
 * Created time: 3:21:05 PM
 */

package dev.sanero.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.HouseHold;
import dev.sanero.repository.HouseholdRepository;

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

  public List<HouseHold> findAllByRoomIdAndComeDateAndLeaveDate(int roomId,
      String comeDate, String leaveDate) {
    try {
      List<HouseHold> list =  repository.findAllByRoomIdAndComeDateAndLeaveDate(roomId, comeDate,
          leaveDate);
      System.out.println(list.size() + "");
      return list;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}
