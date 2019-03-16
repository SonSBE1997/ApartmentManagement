/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: RoomService.java
 * Author: Sanero.
 * Created date: Mar 16, 2019
 * Created time: 12:52:52 PM
 */

package dev.sanero.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import dev.sanero.entity.Room;
import dev.sanero.repository.RoomRepository;
import org.springframework.stereotype.Service;

/*
 * @author Sanero.
 * Created date: Mar 16, 2019
 * Created time: 12:52:52 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class RoomService {
  @Autowired
  RoomRepository repository;
  
  public Room findById(int roomId) {
    try {
      Optional<Room> optional = repository.findById(roomId);
      if(optional.isPresent()) {
        return optional.get();
      }
      return null;
    } catch (Exception e) {
      return null;
    }
  }
}
