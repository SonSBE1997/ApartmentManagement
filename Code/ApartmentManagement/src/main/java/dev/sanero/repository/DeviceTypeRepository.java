/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: DeviceTypeRepository.java
 * Author: Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:02:55 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.DeviceType;

/*
 * @author Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:02:55 PM
 * Description: TODO - 
 */
@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer>{

}
