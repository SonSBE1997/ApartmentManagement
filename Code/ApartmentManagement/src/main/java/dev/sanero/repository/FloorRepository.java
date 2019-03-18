/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: FloorRepository.java
 * Author: Sanero.
 * Created date: Mar 18, 2019
 * Created time: 10:16:01 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Floor;

/*
 * @author Sanero.
 * Created date: Mar 18, 2019
 * Created time: 10:16:01 PM
 * Description: TODO - 
 */
@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer>{

}
