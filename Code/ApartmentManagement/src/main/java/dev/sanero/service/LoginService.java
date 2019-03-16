/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: LoginService.java
 * Author: Sanero.
 * Created date: Mar 11, 2019
 * Created time: 8:41:04 PM
 */

package dev.sanero.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.Employee;
import dev.sanero.repository.EmployeeRepository;
import dev.sanero.utils.Md5Encryptor;

/*
 * @author Sanero.
 * Created date: Mar 11, 2019
 * Created time: 8:41:04 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class LoginService {
  @Autowired
  EmployeeRepository repository;

  /**
   * Author: Sanero.
   * Created date: Mar 11, 2019
   * Created time: 10:37:35 PM
   * Description: TODO - .
   * @param username
   * @param password
   * @return
   */
  public Employee checkLogin(String username, String password) {
    Optional<Employee> optional = (Optional<Employee>) repository
        .findByUsername(username);
    if (optional.isPresent()) {
      Employee employee = optional.get();
      String pass = employee.getPassword();
      if(Md5Encryptor.encrypt(password).equals(pass)) {
        return employee;
      }
    }
    return null;
  }
}
