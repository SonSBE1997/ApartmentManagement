/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Service.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:53:37 PM
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
 * Created time: 10:53:37 PM
 * Description: TODO - 
 */
@Entity(name = "service")
@Table(name = "service")
public class Service implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "start_date")
  private Date startDate;
  @Column(name = "end_date")
  private Date endDate;
  private String detail;
  private double price;
  private String description;
  private boolean paid;
  @Column(name = "created_date")
  private Date createdDate;

  @ManyToOne()
  @JoinColumn(name = "household_id")
  private HouseHold household;

  @ManyToOne()
  @JoinColumn(name = "created_by")
  private Employee employee;

  @ManyToOne()
  @JoinColumn(name = "service_type")
  private ServiceType serviceType;

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:17 PM
   */
  @Override
  public String toString() {
    return "Service [id=" + id + ", startDate=" + startDate + ", endDate="
        + endDate + ", detail=" + detail + ", price=" + price + ", description="
        + description + ", paid=" + paid + ", createdDate=" + createdDate
        + ", household=" + household + ", employee=" + employee
        + ", serviceType=" + serviceType + "]";
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the startDate
   */
  public Date getStartDate() {
    return startDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param startDate the startDate to set
   */
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the endDate
   */
  public Date getEndDate() {
    return endDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param endDate the endDate to set
   */
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the detail
   */
  public String getDetail() {
    return detail;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param detail the detail to set
   */
  public void setDetail(String detail) {
    this.detail = detail;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param price the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the paid
   */
  public boolean isPaid() {
    return paid;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param paid the paid to set
   */
  public void setPaid(boolean paid) {
    this.paid = paid;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the household
   */
  public HouseHold getHousehold() {
    return household;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param household the household to set
   */
  public void setHousehold(HouseHold household) {
    this.household = household;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the employee
   */
  public Employee getEmployee() {
    return employee;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param employee the employee to set
   */
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @return the serviceType
   */
  public ServiceType getServiceType() {
    return serviceType;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:22 PM
   * @param serviceType the serviceType to set
   */
  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:24 PM
   * Description: - .
   */
  public Service() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:58:26 PM
   * Description: - .
   * @param id
   * @param startDate
   * @param endDate
   * @param detail
   * @param price
   * @param description
   * @param paid
   * @param createdDate
   * @param household
   * @param employee
   * @param serviceType
   */
  public Service(int id, Date startDate, Date endDate, String detail,
      double price, String description, boolean paid, Date createdDate,
      HouseHold household, Employee employee, ServiceType serviceType) {
    super();
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.detail = detail;
    this.price = price;
    this.description = description;
    this.paid = paid;
    this.createdDate = createdDate;
    this.household = household;
    this.employee = employee;
    this.serviceType = serviceType;
  }

}
