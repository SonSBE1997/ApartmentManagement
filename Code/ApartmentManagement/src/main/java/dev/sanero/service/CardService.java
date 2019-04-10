/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: CardService.java
 * Author: Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:26:31 PM
 */

package dev.sanero.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.Card;
import dev.sanero.repository.CardRepository;

/*
 * @author Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:26:31 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class CardService {
  @Autowired
  CardRepository repository;

  public List<Card> findAll() {
    try {
      return repository.findAllCardsByDisable(false);
    } catch (Exception e) {
      return null;
    }
  }
  
  public Card findById(String id) {
    try {
      return repository.findById(id).get();
    } catch (Exception e) {
      return null;
    }
  }
  
  public List<Card> findAllCardByUser(int userId) {
    try {
      return repository.findByUserId(userId);
    } catch (Exception e) {
      return null;
    }
  }
  
  public Card save(Card c) {
    try {
      if (!"".equals(c.getCardNumber())) {
        Card origin = findById(c.getCardNumber());
        c.setCardType(origin.getCardType());
      }
      return repository.save(c);
    } catch (Exception e) {
      return null;
    }
  }
  
  public boolean delete(String id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
