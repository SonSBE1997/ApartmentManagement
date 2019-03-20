/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: HouseHoldRepository.java
 * Author: Sanero.
 * Created date: Mar 17, 2019
 * Created time: 3:16:26 PM
 */

package dev.sanero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Employee;
import dev.sanero.entity.HouseHold;

/*
 * @author Sanero.
 * Created date: Mar 17, 2019
 * Created time: 3:16:26 PM
 * Description: TODO - 
 */
@Repository
public interface HouseholdRepository extends JpaRepository<HouseHold, Integer> {
  @Query(value = "select * from household WHERE room_id =?1 and ((come_date between ?2 and ?3) or (leave_date between ?2 and ?3))", nativeQuery = true)
  public List<HouseHold> findAllByRoomIdAndComeDateAndLeaveDate(int roomId,
      String comeDate, String leaveDate);
  
  public List<Employee> findAllHouseHoldsByDisable(boolean disable);
}
