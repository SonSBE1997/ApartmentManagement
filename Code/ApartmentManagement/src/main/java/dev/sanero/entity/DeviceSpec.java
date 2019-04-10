/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: DeviceSpec.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:19:45 PM
 */

package dev.sanero.entity;

import java.io.Serializable;

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
 * Created time: 11:19:45 PM
 * Description: TODO - 
 */
@Entity(name = "device_spec")
@Table(name = "device_spec")
public class DeviceSpec implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "device_id")
  private Device device;
  
  @Column(name = "spec_name")
  private String specName;

  private String val;

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:06 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:06 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:06 PM
   * @return the device
   */
  public Device getDevice() {
    return device;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:06 PM
   * @param device the device to set
   */
  public void setDevice(Device device) {
    this.device = device;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:06 PM
   * @return the specName
   */
  public String getSpecName() {
    return specName;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:06 PM
   * @param specName the specName to set
   */
  public void setSpecName(String specName) {
    this.specName = specName;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:06 PM
   * @return the val
   */
  public String getVal() {
    return val;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:06 PM
   * @param val the val to set
   */
  public void setVal(String val) {
    this.val = val;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:10 PM
   */
  @Override
  public String toString() {
    return "DeviceSpec [id=" + id + ", device=" + device + ", specName="
        + specName + ", val=" + val + "]";
  }

  /**
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:12 PM
   * Description: - .
   * @param id
   * @param device
   * @param specName
   * @param val
   */
  public DeviceSpec(int id, Device device, String specName, String val) {
    super();
    this.id = id;
    this.device = device;
    this.specName = specName;
    this.val = val;
  }

  /**
   * Author: Sanero.
   * Created date: Apr 10, 2019
   * Created time: 10:33:14 PM
   * Description: - .
   */
  public DeviceSpec() {
    super();
    // TODO Auto-generated constructor stub
  }
}
