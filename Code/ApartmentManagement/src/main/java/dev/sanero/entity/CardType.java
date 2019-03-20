/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: CardType.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:24:59 PM
 */

package dev.sanero.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:24:59 PM
 * Description: TODO - 
 */
@Entity(name = "card_type")
@Table(name = "card_type")
public class CardType implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private boolean disable;
  
  public boolean isDisable() {
    return disable;
  }

  public void setDisable(boolean disable) {
    this.disable = disable;
  }
  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:30:47 PM
   * Description: - .
   */
  public CardType() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:30:44 PM
   * Description: - .
   * @param id
   * @param name
   */
  public CardType(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:30:42 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:30:42 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:30:42 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:30:42 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:30:37 PM
   */
  @Override
  public String toString() {
    return "CardType [id=" + id + ", name=" + name + "]";
  }
}
