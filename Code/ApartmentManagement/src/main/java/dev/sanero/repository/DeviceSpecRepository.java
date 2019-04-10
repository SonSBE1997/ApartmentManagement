/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: DeviceSpecRepository.java
 * Author: Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:38:54 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.DeviceSpec;

/*
 * @author Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:38:54 PM
 * Description: TODO - 
 */
@Repository
public interface DeviceSpecRepository extends JpaRepository<DeviceSpec, Integer>{

}
