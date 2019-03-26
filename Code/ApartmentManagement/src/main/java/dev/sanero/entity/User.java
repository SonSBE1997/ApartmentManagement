/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: User.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:03:36 PM
 */

package dev.sanero.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:03:36 PM
 * Description: TODO - 
 */
@Entity(name = "user")
@Table(name = "user")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private boolean gender;
  @Column(name = "phone_number")
  private String phoneNumber;
  private String email;
  private String address;
  @Column(name = "id_card")
  private String idCard;
  @Column(name = "is_head")
  private boolean isHead;
  @Column(name = "is_leave")
  private boolean isLeave;
  @Column(name = "leave_date")
  private Date leaveDate;
  @Column(name = "is_enable")
  private boolean isEnable;

  @Column(name="date_of_birth")
  private Date dateOfBirth;
  

  @ManyToOne()
  @JoinColumn(name = "household_id")
  @JsonIgnoreProperties("users")
  private HouseHold household;
  private boolean disable;

  
  /*
   * Author: Sanero.
   * Created date: Mar 26, 2019
   * Created time: 11:19:40 PM
   * @return the dateOfBirth
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 26, 2019
   * Created time: 11:19:40 PM
   * @param dateOfBirth the dateOfBirth to set
   */
  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

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
   * Created time: 10:16:42 PM
   */
  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", gender=" + gender
        + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address="
        + address + ", idCard=" + idCard + ", isHead=" + isHead + ", isLeave="
        + isLeave + ", leaveDate=" + leaveDate + ", isEnable=" + isEnable
        + ", household=" + household + "]";
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:41 PM
   * Description: - .
   * @param id
   * @param name
   * @param gender
   * @param phoneNumber
   * @param email
   * @param address
   * @param idCard
   * @param isHead
   * @param isLeave
   * @param leaveDate
   * @param isEnable
   * @param household
   */
  public User(int id, String name, boolean gender, String phoneNumber,
      String email, String address, String idCard, boolean isHead,
      boolean isLeave, Date leaveDate, boolean isEnable, HouseHold household) {
    super();
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.address = address;
    this.idCard = idCard;
    this.isHead = isHead;
    this.isLeave = isLeave;
    this.leaveDate = leaveDate;
    this.isEnable = isEnable;
    this.household = household;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:40 PM
   * Description: - .
   */
  public User() {
    super();
    // TODO Auto-generated constructor stub
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the gender
   */
  public boolean isGender() {
    return gender;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param gender the gender to set
   */
  public void setGender(boolean gender) {
    this.gender = gender;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param phoneNumber the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the idCard
   */
  public String getIdCard() {
    return idCard;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param idCard the idCard to set
   */
  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the isHead
   */
  public boolean isHead() {
    return isHead;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param isHead the isHead to set
   */
  public void setHead(boolean isHead) {
    this.isHead = isHead;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the isLeave
   */
  public boolean isLeave() {
    return isLeave;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param isLeave the isLeave to set
   */
  public void setLeave(boolean isLeave) {
    this.isLeave = isLeave;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the leaveDate
   */
  public Date getLeaveDate() {
    return leaveDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param leaveDate the leaveDate to set
   */
  public void setLeaveDate(Date leaveDate) {
    this.leaveDate = leaveDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the isEnable
   */
  public boolean isEnable() {
    return isEnable;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param isEnable the isEnable to set
   */
  public void setEnable(boolean isEnable) {
    this.isEnable = isEnable;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @return the household
   */
  public HouseHold getHousehold() {
    return household;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:16:37 PM
   * @param household the household to set
   */
  public void setHousehold(HouseHold household) {
    this.household = household;
  }
}
