/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: DeptService.java
 * Author: Sanero.
 * Created date: Mar 30, 2019
 * Created time: 7:56:26 PM
 */

package dev.sanero.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.Dept;
import dev.sanero.repository.DeptRepository;

/*
 * @author Sanero.
 * Created date: Mar 30, 2019
 * Created time: 7:56:26 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class DeptService {
  @Autowired
  DeptRepository repository;
  
  public List<Dept> findAll() {
     try {
      return repository.findAllDeptsByDisable(false);
    } catch (Exception e) {
      return null;
    }
  }
  
  public boolean save(Dept dept) {
    try {
      repository.save(dept);
      return true;
    } catch (Exception e) {
      // TODO: handle exception
      return false;
    }
  }
  
  public boolean delete(int id) {
    try {
      Dept dept = repository.findById(id).get();
      dept.setDisable(true);
      repository.save(dept);
      return true;
    } catch (Exception e) {
      // TODO: handle exception
      return false;
    }
  }
}
