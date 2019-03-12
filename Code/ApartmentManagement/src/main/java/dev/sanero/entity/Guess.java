/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Guess.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:18:16 PM
 */

package dev.sanero.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:18:16 PM
 * Description: TODO - 
 */
@Table(name = "guess")
@Entity(name = "guess")
public class Guess implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private boolean gender;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Column(name = "id_card")
  private String idCard;
  @Column(name = "come_time")
  private Date comeTime;
  @Column(name = "leave_time")
  private Date leaveTime;

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @return the gender
   */
  public boolean isGender() {
    return gender;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @param gender the gender to set
   */
  public void setGender(boolean gender) {
    this.gender = gender;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @param phoneNumber the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @return the idCard
   */
  public String getIdCard() {
    return idCard;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @param idCard the idCard to set
   */
  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @return the comeTime
   */
  public Date getComeTime() {
    return comeTime;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @param comeTime the comeTime to set
   */
  public void setComeTime(Date comeTime) {
    this.comeTime = comeTime;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @return the leaveTime
   */
  public Date getLeaveTime() {
    return leaveTime;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:23 PM
   * @param leaveTime the leaveTime to set
   */
  public void setLeaveTime(Date leaveTime) {
    this.leaveTime = leaveTime;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:29 PM
   */
  @Override
  public String toString() {
    return "Guess [id=" + id + ", name=" + name + ", gender=" + gender
        + ", phoneNumber=" + phoneNumber + ", idCard=" + idCard + ", comeTime="
        + comeTime + ", leaveTime=" + leaveTime + "]";
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:31 PM
   * Description: - .
   */
  public Guess() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:20:32 PM
   * Description: - .
   * @param id
   * @param name
   * @param gender
   * @param phoneNumber
   * @param idCard
   * @param comeTime
   * @param leaveTime
   */
  public Guess(int id, String name, boolean gender, String phoneNumber,
      String idCard, Date comeTime, Date leaveTime) {
    super();
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.phoneNumber = phoneNumber;
    this.idCard = idCard;
    this.comeTime = comeTime;
    this.leaveTime = leaveTime;
  }

}
