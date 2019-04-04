/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: UserRepository.java
 * Author: Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:14:16 PM
 */

package dev.sanero.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.User;

/*
 * @author Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:14:16 PM
 * Description: TODO - 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  @Query("SELECT u FROM user u")
  List<User> findByPage(Pageable pageable);

  @Query("SELECT u FROM user u where u.name like ?1 or u.phoneNumber like ?1 or u.address like ?1 or u.idCard like ?1 "
      + "or u.household.room.name like ?1 and u.disable = false and u.isLeave = false")
  List<User> searchByPage(String searchStr, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.name like ?1 or u.phoneNumber like ?1 or u.address like ?1 or u.idCard like ?1 "
      + "or u.household.room.name like ?1 and u.disable = false and u.isLeave = false")
  long searchCount(String searchStr);
  
  // FILTER  
  @Query("SELECT u FROM user u where u.household.room.id = ?1")
  List<User> filterByRoom(int roomId, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.id = ?1")
 long filterByRoomCount(int roomId);
  
  @Query("SELECT u FROM user u where u.household.room.floor.id = ?1")
  List<User> filterByFloor(int floorId, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.floor.id = ?1")
  long filterByFloorCount(int floorId);
  
  @Query("SELECT u FROM user u where u.household.room.building.id = ?1")
  List<User> filterByBuilding(int buildingId, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.building.id = ?1")
  long filterByBuildingCount(int buildingId);
  
  //  FILTER BY STATUS 
  @Query("SELECT u FROM user u where u.household.room.id = ?1 and u.disable = ?2 and u.isLeave = ?3")
  List<User> filterByRoomAndStatus(int roomId, boolean disable, boolean leave, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.id = ?1 and u.disable = ?2 and u.isLeave = ?3")
 long filterByRoomAndStatusCount(int roomId, boolean disable, boolean leave);
  
  @Query("SELECT u FROM user u where u.household.room.floor.id = ?1 and u.disable = ?2 and u.isLeave = ?3")
  List<User> filterByFloorAndStatus(int floorId, boolean disable, boolean leave, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.floor.id = ?1 and u.disable = ?2 and u.isLeave = ?3")
  long filterByFloorAndStatusCount(int floorId, boolean disable, boolean leave);
  
  @Query("SELECT u FROM user u where u.household.room.building.id = ?1 and u.disable = ?2 and u.isLeave = ?3")
  List<User> filterByBuildingAndStatus(int buildingId, boolean disable, boolean leave, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.building.id = ?1 and u.disable = ?2 and u.isLeave = ?3")
  long filterByBuildingAndStatusCount(int buildingId, boolean disable, boolean leave);
  
  @Query("SELECT u FROM user u where u.disable = ?1 and u.isLeave = ?2")
  List<User> filterByStatus(boolean disable, boolean leave, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.disable = ?1 and u.isLeave = ?2")
  long filterByStatusCount(boolean disable, boolean leave);
  

  @Query("SELECT u FROM user u where u.disable = true and u.isLeave = false and leaveDate = ?1")
  List<User> leaveToday(Date now);
  
  @Query("SELECT count(u.id) FROM user u where u.disable = true and u.isLeave = false and leaveDate > ?1")
  long leaveTodayCount(Date now);
  
}
