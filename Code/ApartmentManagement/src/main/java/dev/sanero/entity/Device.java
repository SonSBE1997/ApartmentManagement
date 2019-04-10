/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Device.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:13:16 PM
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
 * Created time: 11:13:16 PM
 * Description: TODO - 
 */
@Entity(name = "device")
@Table(name = "device")
public class Device implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String sign;
  private String provider;
  @Column(name = "installed_date")
  private Date installedDate;
  @Column(name = "operation_date")
  private Date operationDate;
  private String unit;
  private int quantity;
  private double price;
  private String description;
  @Column(name = "maintenance_cycle")
  private int maintenanceCycle;
  private boolean status;
  @ManyToOne
  @JoinColumn(name = "device_type")
  private DeviceType deviceType;
  @ManyToOne
  @JoinColumn(name = "device_group")
  private DeviceGroup deviceGroup;

  @ManyToOne
  @JoinColumn(name = "room_id")
  private Room room;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "device_id")
  @JsonIgnoreProperties(value="device")
  private Set<DeviceSpec> deviceSpec;
  
  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:21:33 PM
   * @return the deviceSpec
   */
  public Set<DeviceSpec> getDeviceSpec() {
    return deviceSpec;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:21:33 PM
   * @param deviceSpec the deviceSpec to set
   */
  public void setDeviceSpec(Set<DeviceSpec> deviceSpec) {
    this.deviceSpec = deviceSpec;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 9:56:07 PM
   * @return the room
   */
  public Room getRoom() {
    return room;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 9:56:07 PM
   * @param room the room to set
   */
  public void setRoom(Room room) {
    this.room = room;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:18:54 PM
   */
  @Override
  public String toString() {
    return "Device [id=" + id + ", name=" + name + ", sign=" + sign
        + ", provider=" + provider + ", installedDate=" + installedDate
        + ", operationDate=" + operationDate + ", unit=" + unit + ", quantity="
        + quantity + ", price=" + price + ", description=" + description
        + ", maintenanceCycle=" + maintenanceCycle + ", status=" + status
        + ", deviceType=" + deviceType + ", deviceGroup=" + deviceGroup + "]";
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:18:59 PM
   * Description: - .
   */
  public Device() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:02 PM
   * Description: - .
   * @param id
   * @param name
   * @param sign
   * @param provider
   * @param installedDate
   * @param operationDate
   * @param unit
   * @param quantity
   * @param price
   * @param description
   * @param maintenanceCycle
   * @param status
   * @param deviceType
   * @param deviceGroup
   */
  public Device(int id, String name, String sign, String provider,
      Date installedDate, Date operationDate, String unit, int quantity,
      double price, String description, int maintenanceCycle, boolean status,
      DeviceType deviceType, DeviceGroup deviceGroup) {
    super();
    this.id = id;
    this.name = name;
    this.sign = sign;
    this.provider = provider;
    this.installedDate = installedDate;
    this.operationDate = operationDate;
    this.unit = unit;
    this.quantity = quantity;
    this.price = price;
    this.description = description;
    this.maintenanceCycle = maintenanceCycle;
    this.status = status;
    this.deviceType = deviceType;
    this.deviceGroup = deviceGroup;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the sign
   */
  public String getSign() {
    return sign;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param sign the sign to set
   */
  public void setSign(String sign) {
    this.sign = sign;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the provider
   */
  public String getProvider() {
    return provider;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param provider the provider to set
   */
  public void setProvider(String provider) {
    this.provider = provider;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the installedDate
   */
  public Date getInstalledDate() {
    return installedDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param installedDate the installedDate to set
   */
  public void setInstalledDate(Date installedDate) {
    this.installedDate = installedDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the operationDate
   */
  public Date getOperationDate() {
    return operationDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param operationDate the operationDate to set
   */
  public void setOperationDate(Date operationDate) {
    this.operationDate = operationDate;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the unit
   */
  public String getUnit() {
    return unit;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param unit the unit to set
   */
  public void setUnit(String unit) {
    this.unit = unit;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param quantity the quantity to set
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param price the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the maintenanceCycle
   */
  public int getMaintenanceCycle() {
    return maintenanceCycle;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param maintenanceCycle the maintenanceCycle to set
   */
  public void setMaintenanceCycle(int maintenanceCycle) {
    this.maintenanceCycle = maintenanceCycle;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the status
   */
  public boolean isStatus() {
    return status;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param status the status to set
   */
  public void setStatus(boolean status) {
    this.status = status;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the deviceType
   */
  public DeviceType getDeviceType() {
    return deviceType;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param deviceType the deviceType to set
   */
  public void setDeviceType(DeviceType deviceType) {
    this.deviceType = deviceType;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @return the deviceGroup
   */
  public DeviceGroup getDeviceGroup() {
    return deviceGroup;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:19:08 PM
   * @param deviceGroup the deviceGroup to set
   */
  public void setDeviceGroup(DeviceGroup deviceGroup) {
    this.deviceGroup = deviceGroup;
  }

}
