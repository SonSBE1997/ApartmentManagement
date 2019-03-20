/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Card.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:31:05 PM
 */

package dev.sanero.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:31:05 PM
 * Description: TODO - 
 */
@Entity(name = "card")
@Table(name = "card")
public class Card implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "card_numb")
  private String cardNumber;

  @Column(name = "created_date")
  private Date createdDate;

  @ManyToOne
  @JoinColumn(name = "card_type_id")
  private CardType cardType;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "created_by")
  private Employee employee;
  
  private boolean disable;
  
  public boolean isDisable() {
    return disable;
  }

  public void setDisable(boolean disable) {
    this.disable = disable;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:18 PM
   */
  @Override
  public String toString() {
    return "Card [cardNumber=" + cardNumber + ", createdDate=" + createdDate
        + ", cardType=" + cardType + ", user=" + user + ", employee=" + employee
        + "]";
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:21 PM
   * Description: - .
   * @param cardNumber
   * @param createdDate
   * @param cardType
   * @param user
   * @param employee
   */
  public Card(String cardNumber, Date createdDate, CardType cardType, User user,
      Employee employee) {
    super();
    this.cardNumber = cardNumber;
    this.createdDate = createdDate;
    this.cardType = cardType;
    this.user = user;
    this.employee = employee;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:22 PM
   * Description: - .
   */
  public Card() {
    super();
    // TODO Auto-generated constructor stub
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:28 PM
   * @return the cardNumber
   */
  public String getCardNumber() {
    return cardNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:28 PM
   * @param cardNumber the cardNumber to set
   */
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:28 PM
   * @return the createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:28 PM
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:28 PM
   * @return the cardType
   */
  public CardType getCardType() {
    return cardType;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:28 PM
   * @param cardType the cardType to set
   */
  public void setCardType(CardType cardType) {
    this.cardType = cardType;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:28 PM
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:28 PM
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:28 PM
   * @return the employee
   */
  public Employee getEmployee() {
    return employee;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:34:28 PM
   * @param employee the employee to set
   */
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
}
