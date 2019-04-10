/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: DeviceGroupRepository.java
 * Author: Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:04:07 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.DeviceGroup;

/*
 * @author Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:04:07 PM
 * Description: TODO - 
 */
@Repository
public interface DeviceGroupRepository extends JpaRepository<DeviceGroup, Integer>{

}
