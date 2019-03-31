/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: DeptRepository.java
 * Author: Sanero.
 * Created date: Mar 30, 2019
 * Created time: 7:54:51 PM
 */

package dev.sanero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Dept;

/*
 * @author Sanero.
 * Created date: Mar 30, 2019
 * Created time: 7:54:51 PM
 * Description: TODO - 
 */
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer>{
  public List<Dept> findAllDeptsByDisable(boolean disable);
}
