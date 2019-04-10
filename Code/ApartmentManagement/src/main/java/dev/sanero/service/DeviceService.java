/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: DeviceService.java
 * Author: Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:51:08 PM
 */

package dev.sanero.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.Device;
import dev.sanero.entity.DeviceGroup;
import dev.sanero.entity.DeviceSpec;
import dev.sanero.entity.DeviceType;
import dev.sanero.repository.DeviceGroupRepository;
import dev.sanero.repository.DeviceRepository;
import dev.sanero.repository.DeviceSpecRepository;
import dev.sanero.repository.DeviceTypeRepository;

/*
 * @author Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:51:08 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class DeviceService {
  @Autowired
  DeviceRepository repository;
  @Autowired
  DeviceTypeRepository typeRepository;
  @Autowired
  DeviceGroupRepository groupRepository;
  @Autowired
  DeviceSpecRepository specRepository;

  public List<DeviceType> findAllType() {
    try {
      return typeRepository.findAll();
    } catch (Exception e) {
      return null;
    }
  }

  public List<DeviceGroup> findAllGroup() {
    try {
      return groupRepository.findAll();
    } catch (Exception e) {
      return null;
    }
  }

  public List<Device> findAll() {
    try {
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }
  
  public List<Device> findAllByGroup(int groupId) {
    try {
      return repository.findAllByGroup(groupId);
    } catch (Exception e) {
      return null;
    }
  }

  public Device saveDevice(Device d) {
    try {
      if (d.getId() != 0) {
        Device origin = repository.findById(d.getId()).get();
        d.setDeviceGroup(origin.getDeviceGroup());
        d.setDeviceType(origin.getDeviceType());
        d.setDeviceSpec(origin.getDeviceSpec());
        d.setRoom(origin.getRoom());
      }
      return repository.save(d);
    } catch (Exception e) {
      return null;
    }
  }
  
  public boolean save(Device d) {
     try {
       d = saveDevice(d);
       if (d == null) 
         return false;
       d.getDeviceSpec().forEach(spec -> {
         saveDeviceSpec(spec);
       });
       return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean saveDeviceSpec(DeviceSpec spec) {
    try {
      if (spec.getId() != 0) {
        DeviceSpec origin = specRepository.findById(spec.getId()).get();
        spec.setDevice(origin.getDevice());
      }
      specRepository.save(spec);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
