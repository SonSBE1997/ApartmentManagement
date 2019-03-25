/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: UserService.java
 * Author: Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:24:26 PM
 */

package dev.sanero.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.User;
import dev.sanero.repository.UserRepository;

/*
 * @author Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:24:26 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class UserService {
  @Autowired
  UserRepository repository;

  public List<User> findAll() {
    try {
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }

  public boolean deleteById(int id) {
    try {
      Optional<User> op = repository.findById(id);
      if (op.isPresent()) {
        User u = op.get();
        u.setDisable(true);
        repository.save(u);
        return true;
      }
      return false;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public boolean save(User u) {
    try {
      repository.save(u);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
