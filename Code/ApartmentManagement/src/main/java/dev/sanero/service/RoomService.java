/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: RoomService.java
 * Author: Sanero.
 * Created date: Mar 16, 2019
 * Created time: 12:52:52 PM
 */

package dev.sanero.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.Room;
import dev.sanero.repository.RoomRepository;

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
  
  public List<Room> findAll() {
    try {
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }

  public boolean deleteById(int id) {
    try {
      long beforeDelete = repository.count();
      repository.deleteById(id);
      long afterDelete  = repository.count();
      if(beforeDelete > afterDelete) {
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean save(List<Room> rooms) {
    try {
      for (Room r : rooms) {
        repository.save(r);
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean deleteAll(List<Integer> rooms) {
    int size = rooms.size();
    if(size == 0) {
      return false;
    }
    for(int i = 0; i < size; i++) {
      deleteById(rooms.get(i));
    }
    return true;
  }
}
