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
  @Query("SELECT u FROM user u where u.household.comeDate <= current_date()")
  List<User> findByPage(Pageable pageable);

  @Query("SELECT u FROM user u where u.name like ?1 or u.phoneNumber like ?1 or u.address like ?1 or u.idCard like ?1 "
      + "or u.household.room.name like ?1 and u.disable = false and u.isLeave = false and u.household.comeDate <= current_date()")
  List<User> searchByPage(String searchStr, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.name like ?1 or u.phoneNumber like ?1 or u.address like ?1 or u.idCard like ?1 "
      + "or u.household.room.name like ?1 and u.disable = false and u.isLeave = false and u.household.comeDate <= current_date()")
  long searchCount(String searchStr);
  
  // FILTER  
  @Query("SELECT u FROM user u where u.household.room.id = ?1 and u.household.comeDate <= current_date()")
  List<User> filterByRoom(int roomId, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.id = ?1 and u.household.comeDate <= current_date()")
 long filterByRoomCount(int roomId);
  
  @Query("SELECT u FROM user u where u.household.room.floor.id = ?1 and u.household.comeDate <= current_date()")
  List<User> filterByFloor(int floorId, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.floor.id = ?1 and u.household.comeDate <= current_date()")
  long filterByFloorCount(int floorId);
  
  @Query("SELECT u FROM user u where u.household.room.building.id = ?1 and u.household.comeDate <= current_date()")
  List<User> filterByBuilding(int buildingId, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.building.id = ?1 and u.household.comeDate <= current_date()")
  long filterByBuildingCount(int buildingId);
  
  //  FILTER BY STATUS 
  @Query("SELECT u FROM user u where u.household.room.id = ?1 and u.disable = ?2 and u.isLeave = ?3 and u.household.comeDate <= current_date()")
  List<User> filterByRoomAndStatus(int roomId, boolean disable, boolean leave, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.id = ?1 and u.disable = ?2 and u.isLeave = ?3 and u.household.comeDate <= current_date()")
 long filterByRoomAndStatusCount(int roomId, boolean disable, boolean leave);
  
  @Query("SELECT u FROM user u where u.household.room.floor.id = ?1 and u.disable = ?2 and u.isLeave = ?3 and u.household.comeDate <= current_date()")
  List<User> filterByFloorAndStatus(int floorId, boolean disable, boolean leave, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.floor.id = ?1 and u.disable = ?2 and u.isLeave = ?3 and u.household.comeDate <= current_date()")
  long filterByFloorAndStatusCount(int floorId, boolean disable, boolean leave);
  
  @Query("SELECT u FROM user u where u.household.room.building.id = ?1 and u.disable = ?2 and u.isLeave = ?3 and u.household.comeDate <= current_date()")
  List<User> filterByBuildingAndStatus(int buildingId, boolean disable, boolean leave, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.household.room.building.id = ?1 and u.disable = ?2 and u.isLeave = ?3 and u.household.comeDate <= current_date()")
  long filterByBuildingAndStatusCount(int buildingId, boolean disable, boolean leave);
  
  @Query("SELECT u FROM user u where u.disable = ?1 and u.isLeave = ?2 and u.household.comeDate <= current_date()")
  List<User> filterByStatus(boolean disable, boolean leave, Pageable pageable);
  
  @Query("SELECT count(u.id) FROM user u where u.disable = ?1 and u.isLeave = ?2 and u.household.comeDate <= current_date()")
  long filterByStatusCount(boolean disable, boolean leave);
  

  @Query("SELECT u FROM user u where u.disable = true and leaveDate = ?1")
  List<User> leaveToday(Date now);
  
  @Query("SELECT count(u.id) FROM user u where u.disable = true and leaveDate > ?1")
  long leaveTodayCount(Date now);
  
}
