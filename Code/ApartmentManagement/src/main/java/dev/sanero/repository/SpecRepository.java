/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: SpecRepository.java
 * Author: Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:38:16 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Spec;

/*
 * @author Sanero.
 * Created date: Apr 10, 2019
 * Created time: 10:38:16 PM
 * Description: TODO - 
 */
@Repository
public interface SpecRepository extends JpaRepository<Spec, Integer>{

}
