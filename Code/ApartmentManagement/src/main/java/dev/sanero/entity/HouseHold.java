/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: HouseHold.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 9:54:39 PM
 */

package dev.sanero.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 9:54:39 PM
 * Description: TODO - 
 */
@Table(name = "household")
@Entity(name = "household")
public class HouseHold implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "fullname")
  private String fullName;
  @Column(name = "id_card", nullable = true)
  private String idCard;
  private String address;
  @Column(name = "phone_number", nullable = true)
  private String phoneNumber;
  private String email;
  @Column(name = "come_date")
  private Date comeDate;
  @Column(name = "leave_date", nullable = true)
  private Date leaveDate;
  @Column(name = "is_hire")
  private boolean isHire;
  private double price;
  private double deposit;
  @Column(name = "deposit_date")
  private Date depositDate;
  private String status; // 0: chua ban giao, 1: da ban giao, 2: da chuyen di, 3: se chuyen di , 4: da huy dang ky chuyen den, 
  @Column(name = "user_id")
  private int userId;
  @ManyToOne
  @JoinColumn(name = "room_id")
  @JsonIgnoreProperties(value = "households", allowSetters = true)
  private Room room;

  @ManyToOne
  @JoinColumn(name = "created_by")
  @JsonIgnoreProperties("dept")
  private Employee employee;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "household_id")
  @JsonIgnoreProperties(value = "household", allowSetters = true)
  private Set<User> users;
  private boolean disable;

  /*
   * Author: Sanero.
   * Created date: May 18, 2019
   * Created time: 9:05:37 PM
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /*
   * Author: Sanero.
   * Created date: May 18, 2019
   * Created time: 9:05:37 PM
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /*
   * Author: Sanero.
   * Created date: May 18, 2019
   * Created time: 8:23:50 PM
   * @return the userId
   */
  public int getUserId() {
    return userId;
  }

  /*
   * Author: Sanero.
   * Created date: May 18, 2019
   * Created time: 8:23:50 PM
   * @param userId the userId to set
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  public boolean isDisable() {
    return disable;
  }

  public void setDisable(boolean disable) {
    this.disable = disable;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:11 PM
   * Description: - .
   * @param id
   * @param fullName
   * @param idCard
   * @param address
   * @param phoneNumber
   * @param comeDate
   * @param leaveDate
   * @param isHire
   * @param price
   * @param deposit
   * @param depositDate
   * @param status
   * @param room
   * @param employee
   */
  public HouseHold(int id, String fullName, String idCard, String address,
      String phoneNumber, Date comeDate, Date leaveDate, boolean isHire,
      double price, double deposit, Date depositDate, String status, Room room,
      Employee employee) {
    super();
    this.id = id;
    this.fullName = fullName;
    this.idCard = idCard;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.comeDate = comeDate;
    this.leaveDate = leaveDate;
    this.isHire = isHire;
    this.price = price;
    this.deposit = deposit;
    this.depositDate = depositDate;
    this.status = status;
    this.room = room;
    this.employee = employee;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:18:07 PM
   * @return the users
   */
  public Set<User> getUsers() {
    return users;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:18:07 PM
   * @param users the users to set
   */
  public void setUsers(Set<User> users) {
    this.users = users;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:10 PM
   * Description: - .
   */
  public HouseHold() {
    super();
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:06 PM
   */
  @Override
  public String toString() {
    return "HouseHold [id=" + id + ", fullName=" + fullName + ", idCard="
        + idCard + ", address=" + address + ", phoneNumber=" + phoneNumber
        + ", comeDate=" + comeDate + ", leaveDate=" + leaveDate + ", isHire="
        + isHire + ", price=" + price + ", deposit=" + deposit
        + ", depositDate=" + depositDate + ", status=" + status + ", room="
        + room + ", employee=" + employee + "]";
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the fullName
   */
  public String getFullName() {
    return fullName;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param fullName the fullName to set
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the idCard
   */
  public String getIdCard() {
    return idCard;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param idCard the idCard to set
   */
  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param phoneNumber the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the comeDate
   */
  public Date getComeDate() {
    return comeDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param comeDate the comeDate to set
   */
  public void setComeDate(Date comeDate) {
    this.comeDate = comeDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the leaveDate
   */
  public Date getLeaveDate() {
    return leaveDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param leaveDate the leaveDate to set
   */
  public void setLeaveDate(Date leaveDate) {
    this.leaveDate = leaveDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the isHire
   */
  public boolean isHire() {
    return isHire;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param isHire the isHire to set
   */
  public void setHire(boolean isHire) {
    this.isHire = isHire;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param price the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the deposit
   */
  public double getDeposit() {
    return deposit;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param deposit the deposit to set
   */
  public void setDeposit(double deposit) {
    this.deposit = deposit;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the depositDate
   */
  public Date getDepositDate() {
    return depositDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param depositDate the depositDate to set
   */
  public void setDepositDate(Date depositDate) {
    this.depositDate = depositDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the room
   */
  public Room getRoom() {
    return room;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param room the room to set
   */
  public void setRoom(Room room) {
    this.room = room;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @return the employee
   */
  public Employee getEmployee() {
    return employee;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:00:03 PM
   * @param employee the employee to set
   */
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

}
