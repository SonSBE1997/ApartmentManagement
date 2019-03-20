/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Employee.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 9:18:14 PM
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

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 9:18:14 PM
 * Description: TODO - 
 */
@Table(name = "employee")
@Entity(name = "employee")
public class Employee implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private boolean gender;
  @Column(name = "date_of_birth")
  private Date dateOfBirth;
  @Column(name = "phone_number")
  private String phoneNumber;
  private String email;
  private String address;
  @Column(name = "id_card")
  private String idCard;
  private String username;
  private String password;
  private String role;
  private boolean disable;

  public boolean isDisable() {
    return disable;
  }

  public void setDisable(boolean disable) {
    this.disable = disable;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:45:20 PM
   * @return the role
   */
  public String getRole() {
    return role;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:45:20 PM
   * @param role the role to set
   */
  public void setRole(String role) {
    this.role = role;
  }

  @Column(name = "is_manager")
  private boolean isManager;
  @ManyToOne
  @JoinColumn(name = "dept_id")
  private Dept dept;

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the gender
   */
  public boolean isGender() {
    return gender;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param gender the gender to set
   */
  public void setGender(boolean gender) {
    this.gender = gender;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the dateOfBirth
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param dateOfBirth the dateOfBirth to set
   */
  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param phoneNumber the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the idCard
   */
  public String getIdCard() {
    return idCard;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param idCard the idCard to set
   */
  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the isManager
   */
  public boolean isManager() {
    return isManager;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param isManager the isManager to set
   */
  public void setManager(boolean isManager) {
    this.isManager = isManager;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @return the dept
   */
  public Dept getDept() {
    return dept;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:15 PM
   * @param dept the dept to set
   */
  public void setDept(Dept dept) {
    this.dept = dept;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:23 PM
   * Description: - .
   */
  public Employee() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:23:28 PM
   * Description: - .
   * @param id
   * @param name
   * @param gender
   * @param dateOfBirth
   * @param phoneNumber
   * @param email
   * @param address
   * @param idCard
   * @param username
   * @param password
   * @param isManager
   * @param dept
   */
  public Employee(int id, String name, boolean gender, Date dateOfBirth,
      String phoneNumber, String email, String address, String idCard,
      String username, String password, boolean isManager, Dept dept) {
    super();
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.address = address;
    this.idCard = idCard;
    this.username = username;
    this.password = password;
    this.isManager = isManager;
    this.dept = dept;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 16, 2019
   * Created time: 8:37:53 PM
   */
  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + ", gender=" + gender
        + ", dateOfBirth=" + dateOfBirth + ", phoneNumber=" + phoneNumber
        + ", email=" + email + ", address=" + address + ", idCard=" + idCard
        + ", username=" + username + ", password=" + password + ", role=" + role
        + ", isManager=" + isManager + ", dept=" + dept + "]";
  }
}
