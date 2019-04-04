/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: EmployeeService.java
 * Author: Sanero.
 * Created date: Mar 30, 2019
 * Created time: 8:05:46 PM
 */

package dev.sanero.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.Employee;
import dev.sanero.repository.EmployeeRepository;
import dev.sanero.utils.GenerateRandomString;
import dev.sanero.utils.Md5Encryptor;

/*
 * @author Sanero.
 * Created date: Mar 30, 2019
 * Created time: 8:05:46 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class EmployeeService {
  @Autowired
  EmployeeRepository repository;

  @Autowired
  MailService mailService;

  public List<Employee> findAll() {
    try {
      return repository.findAllEmployeesByDisable(false);
    } catch (Exception e) {
      return null;
    }
  }

  public Employee findById(int id) {
    try {
      return repository.findById(id).get();
    } catch (Exception e) {
      return null;
    }
  }

  public int save(Employee emp) {
    try {
      if (emp.getId() == 0) {
        Optional<Employee> op = repository.findByUsername(emp.getUsername());
        if (op.isPresent()) {
          if (op.get() != null)
            return 2;
        }
      }
      if ("".equals(emp.getPassword())) {
        String password = GenerateRandomString
            .givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect();
        mailService.sendMail("sonvuongso@gmail.com", mailService
            .genContentMail(emp.getName(), emp.getUsername(), password));
        emp.setPassword(Md5Encryptor.encrypt(password));
      }
      repository.save(emp);
      return 1;
    } catch (Exception e) {
      // TODO: handle exception
      return -1;
    }
  }

  public boolean delete(int id) {
    try {
      Employee emp = repository.findById(id).get();
      emp.setDisable(true);
      repository.save(emp);
      return true;
    } catch (Exception e) {
      // TODO: handle exception
      return false;
    }
  }
}
