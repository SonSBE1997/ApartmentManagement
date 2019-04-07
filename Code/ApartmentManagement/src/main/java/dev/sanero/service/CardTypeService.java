/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: CardTypeService.java
 * Author: Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:26:39 PM
 */

package dev.sanero.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sanero.entity.CardType;
import dev.sanero.repository.CardTypeRepository;

/*
 * @author Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:26:39 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class CardTypeService {
  @Autowired
  CardTypeRepository repository;

  public List<CardType> findAll() {
    try {
      return repository.findAll();
    } catch (Exception e) {
      return null;
    }
  }
}
