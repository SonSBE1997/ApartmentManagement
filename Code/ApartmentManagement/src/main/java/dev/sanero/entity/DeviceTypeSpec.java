/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: DeviceTypeSpec.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:11:15 PM
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
 * Created time: 11:11:15 PM
 * Description: TODO - 
 */
@Entity(name = "device_type_spec")
@Table(name = "device_type_spec")
public class DeviceTypeSpec implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne()
  @JoinColumn(name = "spec_id")
  private Spec spec;

  @ManyToOne()
  @JoinColumn(name = "type_id")
  private DeviceType deviceType;

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:12:57 PM
   */
  @Override
  public String toString() {
    return "DeviceTypeSpec [id=" + id + ", spec=" + spec + ", deviceType="
        + deviceType + "]";
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:12:59 PM
   * Description: - .
   */
  public DeviceTypeSpec() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:13:01 PM
   * Description: - .
   * @param id
   * @param spec
   * @param deviceType
   */
  public DeviceTypeSpec(int id, Spec spec, DeviceType deviceType) {
    super();
    this.id = id;
    this.spec = spec;
    this.deviceType = deviceType;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:13:04 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:13:04 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:13:04 PM
   * @return the spec
   */
  public Spec getSpec() {
    return spec;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:13:04 PM
   * @param spec the spec to set
   */
  public void setSpec(Spec spec) {
    this.spec = spec;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:13:04 PM
   * @return the deviceType
   */
  public DeviceType getDeviceType() {
    return deviceType;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:13:04 PM
   * @param deviceType the deviceType to set
   */
  public void setDeviceType(DeviceType deviceType) {
    this.deviceType = deviceType;
  }
}
