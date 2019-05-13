/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: ServiceRepository.java
 * Author: Sanero.
 * Created date: May 11, 2019
 * Created time: 1:59:27 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Service;

/*
 * @author Sanero.
 * Created date: May 11, 2019
 * Created time: 1:59:27 PM
 * Description: TODO - 
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
