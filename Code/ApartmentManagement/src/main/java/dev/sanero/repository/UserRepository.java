/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: UserRepository.java
 * Author: Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:14:16 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.User;

/*
 * @author Sanero.
 * Created date: Mar 25, 2019
 * Created time: 11:14:16 PM
 * Description: TODO - 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
