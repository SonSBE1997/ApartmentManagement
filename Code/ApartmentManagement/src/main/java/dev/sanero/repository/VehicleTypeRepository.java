/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: VehicleTypeRepository.java
 * Author: Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:22:53 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.VehicleType;

/*
 * @author Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:22:53 PM
 * Description: TODO - 
 */
@Repository
public interface VehicleTypeRepository  extends JpaRepository<VehicleType , Integer>{

}
