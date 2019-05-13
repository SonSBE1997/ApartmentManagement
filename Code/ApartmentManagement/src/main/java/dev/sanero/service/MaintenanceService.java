/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: MaintenanaceService.java
 * Author: Sanero.
 * Created date: May 2, 2019
 * Created time: 10:04:09 PM
 */

package dev.sanero.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.DeviceGroup;
import dev.sanero.entity.Maintenance;
import dev.sanero.entity.MaintenanceDetail;
import dev.sanero.entity.MaintenancePersonal;
import dev.sanero.entity.Room;
import dev.sanero.repository.DeviceGroupRepository;
import dev.sanero.repository.MaintenanceDetailRepository;
import dev.sanero.repository.MaintenancePersonalRepository;
import dev.sanero.repository.MaintenanceRepository;

/*
 * @author Sanero.
 * Created date: May 2, 2019
 * Created time: 10:04:09 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class MaintenanceService {
  @Autowired
  MaintenanceRepository repository;
  
  @Autowired
  MaintenancePersonalRepository personalRepository;
  
  @Autowired
  MaintenanceDetailRepository detailRepository;
  
  @Autowired
  DeviceGroupRepository deviceGroupRepository;
  
  @Autowired
  RoomService roomService;
  
  public List<Maintenance> findByGroupId(int groupId) {
    try {
      return repository.findByGroupId(groupId);
    } catch (Exception e) {
      return null;
    }
  }
  
  public boolean save(Maintenance m) {
    try {
      DeviceGroup deviceGroup = deviceGroupRepository.findById(m.getDeviceGroup().getId()).get();
      m.setDeviceGroup(deviceGroup);
      for (MaintenancePersonal p : m.getPersons()) {
        p = personalRepository.save(p);
      }
      
      for (MaintenanceDetail d : m.getDetails()) {
        d = detailRepository.save(d);
      }
      
      m = repository.save(m);
      if (m.getId() > 0) {        
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }
  
  public List<Maintenance> findByRoom(int id) {
    try {
      Room r = roomService.findById(id);
      String room = r.getBuilding().getName() +  " - " + r.getFloor().getName() + " - " + r.getName();
      return repository.findByRoom(room);
    } catch (Exception e) {
      return null;
    }
  }
}
