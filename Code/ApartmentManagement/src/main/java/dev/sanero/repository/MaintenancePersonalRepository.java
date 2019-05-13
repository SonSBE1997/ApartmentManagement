/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: MaintenancePersonalRepository.java
 * Author: Sanero.
 * Created date: May 5, 2019
 * Created time: 6:06:49 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.MaintenancePersonal;

/*
 * @author Sanero.
 * Created date: May 5, 2019
 * Created time: 6:06:49 PM
 * Description: TODO - 
 */
@Repository
public interface MaintenancePersonalRepository extends JpaRepository<MaintenancePersonal, Integer>{

}
