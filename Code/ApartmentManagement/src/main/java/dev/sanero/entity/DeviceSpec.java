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
  private String name;

  @ManyToOne
  @JoinColumn(name = "spec_id")
  private Spec spec;

  @ManyToOne
  @JoinColumn(name = "device_id")
  private Device device;

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:23:54 PM
   */
  @Override
  public String toString() {
    return "DeviceSpec [id=" + id + ", name=" + name + ", spec=" + spec
        + ", device=" + device + "]";
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:23:56 PM
   * Description: - .
   * @param id
   * @param name
   * @param spec
   * @param device
   */
  public DeviceSpec(int id, String name, Spec spec, Device device) {
    super();
    this.id = id;
    this.name = name;
    this.spec = spec;
    this.device = device;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:23:58 PM
   * Description: - .
   */
  public DeviceSpec() {
    super();
    // TODO Auto-generated constructor stub
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:24:05 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:24:05 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:24:05 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:24:05 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:24:05 PM
   * @return the spec
   */
  public Spec getSpec() {
    return spec;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:24:05 PM
   * @param spec the spec to set
   */
  public void setSpec(Spec spec) {
    this.spec = spec;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:24:05 PM
   * @return the device
   */
  public Device getDevice() {
    return device;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:24:05 PM
   * @param device the device to set
   */
  public void setDevice(Device device) {
    this.device = device;
  }

}
