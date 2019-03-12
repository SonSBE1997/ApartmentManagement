/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Maintenance.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:25:28 PM
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
 * Created time: 11:25:28 PM
 * Description: TODO - 
 */
@Entity(name = "maintenance")
@Table(name = "maintenance")
public class Maintenance implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "maintenance_date")
  private Date maintenanceDate;
  private String description;
  @Column(name = "maintenance_price")
  private double maintenancePrice;
  @Column(name = "is_excuted")
  private boolean isExecuted;
  @Column(name = "number_personnel")
  private int numberPersonnel;

  private boolean paid;

  @ManyToOne
  @JoinColumn(name = "device_group")
  private DeviceGroup deviceGroup;

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @return the maintenanceDate
   */
  public Date getMaintenanceDate() {
    return maintenanceDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @param maintenanceDate the maintenanceDate to set
   */
  public void setMaintenanceDate(Date maintenanceDate) {
    this.maintenanceDate = maintenanceDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @return the maintenancePrice
   */
  public double getMaintenancePrice() {
    return maintenancePrice;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @param maintenancePrice the maintenancePrice to set
   */
  public void setMaintenancePrice(double maintenancePrice) {
    this.maintenancePrice = maintenancePrice;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @return the isExecuted
   */
  public boolean isExecuted() {
    return isExecuted;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @param isExecuted the isExecuted to set
   */
  public void setExecuted(boolean isExecuted) {
    this.isExecuted = isExecuted;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @return the numberPersonnel
   */
  public int getNumberPersonnel() {
    return numberPersonnel;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @param numberPersonnel the numberPersonnel to set
   */
  public void setNumberPersonnel(int numberPersonnel) {
    this.numberPersonnel = numberPersonnel;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @return the paid
   */
  public boolean isPaid() {
    return paid;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @param paid the paid to set
   */
  public void setPaid(boolean paid) {
    this.paid = paid;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @return the deviceGroup
   */
  public DeviceGroup getDeviceGroup() {
    return deviceGroup;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:41 PM
   * @param deviceGroup the deviceGroup to set
   */
  public void setDeviceGroup(DeviceGroup deviceGroup) {
    this.deviceGroup = deviceGroup;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:44 PM
   * Description: - .
   * @param id
   * @param maintenanceDate
   * @param description
   * @param maintenancePrice
   * @param isExecuted
   * @param numberPersonnel
   * @param paid
   * @param deviceGroup
   */
  public Maintenance(int id, Date maintenanceDate, String description,
      double maintenancePrice, boolean isExecuted, int numberPersonnel,
      boolean paid, DeviceGroup deviceGroup) {
    super();
    this.id = id;
    this.maintenanceDate = maintenanceDate;
    this.description = description;
    this.maintenancePrice = maintenancePrice;
    this.isExecuted = isExecuted;
    this.numberPersonnel = numberPersonnel;
    this.paid = paid;
    this.deviceGroup = deviceGroup;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:46 PM
   * Description: - .
   */
  public Maintenance() {
    super();
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:31:47 PM
   */
  @Override
  public String toString() {
    return "Maintenance [id=" + id + ", maintenanceDate=" + maintenanceDate
        + ", description=" + description + ", maintenancePrice="
        + maintenancePrice + ", isExecuted=" + isExecuted + ", numberPersonnel="
        + numberPersonnel + ", paid=" + paid + ", deviceGroup=" + deviceGroup
        + "]";
  }

}
