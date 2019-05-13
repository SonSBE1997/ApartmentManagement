/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: ServiceRepository.java
 * Author: Sanero.
 * Created date: May 11, 2019
 * Created time: 2:00:26 PM
 */

package dev.sanero.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.HouseHold;
import dev.sanero.entity.Room;
import dev.sanero.entity.ServiceType;
import dev.sanero.entity.User;
import dev.sanero.repository.ServiceRepository;
import dev.sanero.repository.ServiceTypeRepository;

/*
 * @author Sanero.
 * Created date: May 11, 2019
 * Created time: 2:00:26 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class ServiceService {
  @Autowired
  ServiceTypeRepository typeRepository;

  @Autowired
  ServiceRepository repository;
  
  @Autowired
  EmployeeService empService;

  @Autowired
  MailService mailService;
  
  public List<ServiceType> findAllType() {
    try {
      return typeRepository.findAll();
    } catch (Exception e) {
      return null;
    }
  }
  
  public List<dev.sanero.entity.Service> findAll() {
    try {
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }
  
  public boolean save(dev.sanero.entity.Service s) {
    try {
      if (s.getId() > 0) {
        s.setServiceType(s.getServiceType());
        s.setRoom(s.getRoom());
        s.setEmployee(s.getEmployee());
      }
      s.setEmployee(empService.findById(s.getEmployee().getId()));
      s = repository.save(s);
      if (s.getId() > 0) {
        return true;
      } 
      return false;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean saveType(ServiceType t) {
    try {
      t = typeRepository.save(t);
      if (t.getId() > 0) {
        return true;
      } 
      return false;
    } catch (Exception e) {
      return false;
    }
  }
  
  public boolean deleteType(int id) {
    try {
      typeRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public dev.sanero.entity.Service findById(int id) {
    return repository.findById(id).get();
  }
  
  public boolean remind(dev.sanero.entity.Service s) {
    try {
       Room r = s.getRoom();
       String fullName = "";
       String mailTo = "";
       for (HouseHold v : r.getHouseholds()) {
          if (v.getLeaveDate() == null && v.isStatus() == true) {
            fullName = v.getFullName();
            for (User u: v.getUsers()) {
              if (fullName.equals(u.getName())) {
                mailTo = u.getEmail();
              }
            }
          }
       }
      mailService.sendMail(mailTo, mailService.getInvoiceString(s, fullName).toString(), "Nhắc nhở đóng phí");
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
