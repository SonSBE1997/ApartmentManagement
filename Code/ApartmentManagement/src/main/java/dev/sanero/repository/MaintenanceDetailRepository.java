/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: MaintenanceDetailRepository.java
 * Author: Sanero.
 * Created date: May 5, 2019
 * Created time: 6:08:10 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.MaintenanceDetail;

/*
 * @author Sanero.
 * Created date: May 5, 2019
 * Created time: 6:08:10 PM
 * Description: TODO - 
 */
@Repository
public interface MaintenanceDetailRepository extends JpaRepository<MaintenanceDetail, Integer>{

}
