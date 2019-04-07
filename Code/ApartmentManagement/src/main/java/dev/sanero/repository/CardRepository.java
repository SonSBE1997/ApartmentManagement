/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: CardRepository.java
 * Author: Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:22:39 PM
 */

package dev.sanero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Card;

/*
 * @author Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:22:39 PM
 * Description: TODO - 
 */
@Repository
public interface CardRepository extends JpaRepository<Card, String> {
  public List<Card> findAllCardsByDisable(boolean disable);
  
  @Query("select c from card c WHERE c.user.id = ?1")
  public List<Card> findByUserId(int userId);
}
