/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: CardTypeRepository.java
 * Author: Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:22:23 PM
 */

package dev.sanero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.CardType;

/*
 * @author Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:22:23 PM
 * Description: TODO - 
 */
@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Integer> {

}
