/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: CardController.java
 * Author: Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:46:25 PM
 */

package dev.sanero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sanero.entity.Card;
import dev.sanero.entity.CardType;
import dev.sanero.service.CardService;
import dev.sanero.service.CardTypeService;

/*
 * @author Sanero.
 * Created date: Apr 6, 2019
 * Created time: 1:46:25 PM
 * Description: TODO - 
 */
@RestController
@RequestMapping("/api/card")
public class CardController {
  @Autowired
  CardService cardService;
  @Autowired
  CardTypeService cardTypeService;

  @GetMapping("/type")
  public ResponseEntity<List<CardType>> findAllCardType() {
    return new ResponseEntity<List<CardType>>(cardTypeService.findAll(),
        HttpStatus.OK);
  }
  
  @GetMapping()
  public ResponseEntity<List<Card>> findAllCard() {
    return new ResponseEntity<List<Card>>(cardService.findAll(),
        HttpStatus.OK);
  }

  @GetMapping("/card-user/{userId}")
  public ResponseEntity<List<Card>> findByUserId(@PathVariable int userId) {
    return new ResponseEntity<List<Card>>(cardService.findAllCardByUser(userId),
        HttpStatus.OK);
  }
  
  @GetMapping("/card/{cardId}")
  public ResponseEntity<Card> findById(@PathVariable String cardId) {
    return new ResponseEntity<Card>(cardService.findById(cardId),
        HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteCard(@PathVariable String id) {
    if (cardService.delete(id))
      return new ResponseEntity<String>("Ok", HttpStatus.OK);
    return new ResponseEntity<String>("Not ok", HttpStatus.OK);
  }

  @PostMapping("/save")
  public ResponseEntity<Card> save(@RequestBody Card card) {
    return new ResponseEntity<Card>(cardService.save(card), HttpStatus.OK);
  }
}
