/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: MaintenanceDevice.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:34:13 PM
 */

package dev.sanero.entity;

import java.io.Serializable;

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
 * Created time: 11:34:13 PM
 * Description: TODO - 
 */
@Entity(name = "maintenance_detail")
@Table(name = "maintenance_detail")
public class MaintenanceDetail implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private double price;
  private String description;

  @ManyToOne
  @JoinColumn(name = "device_id")
  private Device device;

  private String location;
  
  
  /*
   * Author: Sanero.
   * Created date: May 5, 2019
   * Created time: 9:08:05 PM
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /*
   * Author: Sanero.
   * Created date: May 5, 2019
   * Created time: 9:08:05 PM
   * @param location the location to set
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:04 PM
   * Description: - .
   * @param id
   * @param price
   * @param description
   * @param device
   * @param maintenance
   */
  public MaintenanceDetail(int id, double price, String description,
      Device device) {
    super();
    this.id = id;
    this.price = price;
    this.description = description;
    this.device = device;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:06 PM
   * Description: - .
   */
  public MaintenanceDetail() {
    super();
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:10 PM
   */
  @Override
  public String toString() {
    return "MaintenanceDetail [id=" + id + ", price=" + price + ", description="
        + description + ", device=" + device + "]";
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:14 PM
   * @return the id
   */
  public int getId() {
    return id;
  } 

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:14 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:14 PM
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:14 PM
   * @param price the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:14 PM
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:14 PM
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:14 PM
   * @return the device
   */
  public Device getDevice() {
    return device;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:36:14 PM
   * @param device the device to set
   */
  public void setDevice(Device device) {
    this.device = device;
  }
}
