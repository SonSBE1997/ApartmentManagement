/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: EmployeeRepository.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:36:51 PM
 */

package dev.sanero.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Employee;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:36:51 PM
 * Description: TODO - 
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  Optional<Employee> findByUsername(String username);
  boolean existsByUsername(int id);
  public List<Employee> findAllEmployeesByDisable(boolean disable);
}
