/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: PartnerCompany.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:49:38 PM
 */

package dev.sanero.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:49:38 PM
 * Description: TODO - 
 */
@Entity(name = "partner_company")
@Table(name = "partner_company")
public class PartnerCompany implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  @Column(name = "phone_number")
  private String phoneNumber;
  private String email;
  private String address;
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
   * Created time: 10:50:59 PM
   */
  @Override
  public String toString() {
    return "PartnerCompany [id=" + id + ", name=" + name + ", phoneNumber="
        + phoneNumber + ", email=" + email + ", address=" + address + "]";
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:00 PM
   * Description: - .
   * @param id
   * @param name
   * @param phoneNumber
   * @param email
   * @param address
   */
  public PartnerCompany(int id, String name, String phoneNumber, String email,
      String address) {
    super();
    this.id = id;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.address = address;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:01 PM
   * Description: - .
   */
  public PartnerCompany() {
    super();
    // TODO Auto-generated constructor stub
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:07 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:07 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:07 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:07 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:07 PM
   * @return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:07 PM
   * @param phoneNumber the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:07 PM
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:07 PM
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:07 PM
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:51:07 PM
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }
}
