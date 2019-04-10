/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: DeviceRepository.java
 * Author: Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:40:25 PM
 */

package dev.sanero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Device;

/*
 * @author Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:40:25 PM
 * Description: TODO - 
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer>{
  @Query("select d from device d where d.deviceGroup.id = ?1")
  public List<Device> findAllByGroup(int groupId);
}
