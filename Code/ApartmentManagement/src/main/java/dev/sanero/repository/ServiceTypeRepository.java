/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: ServiceTypeReporitoy.java
 * Author: Sanero.
 * Created date: May 11, 2019
 * Created time: 1:58:42 PM
 */

package dev.sanero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.ServiceType;

/*
 * @author Sanero.
 * Created date: May 11, 2019
 * Created time: 1:58:42 PM
 * Description: TODO - 
 */
@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer>{
  @Query("select t from service_type t where t.isFixed = true")
  public List<ServiceType> findListServiceTypeIsFixed();
}
