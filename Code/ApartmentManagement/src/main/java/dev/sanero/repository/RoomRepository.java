/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: RoomRepository.java
 * Author: Sanero.
 * Created date: Mar 16, 2019
 * Created time: 12:51:09 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Room;

/*
 * @author Sanero.
 * Created date: Mar 16, 2019
 * Created time: 12:51:09 PM
 * Description: TODO - 
 */
@Repository
public interface RoomRepository  extends JpaRepository<Room, Integer>{
//  public List<Room> findAllRoomsByDisable(boolean disable);
  @Query("select r from room r where r.name like ?1 and r.floor.name like ?2 and r.building.name like ?3")
  public Room findRoomByName(String name, String floor, String building);
}
