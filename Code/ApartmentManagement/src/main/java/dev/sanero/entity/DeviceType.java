/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: DeviceType.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:08:22 PM
 */

package dev.sanero.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:08:22 PM
 * Description: TODO - 
 */
@Entity(name = "device_type")
@Table(name = "device_type")
public class DeviceType implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private boolean disable;

  public boolean isDisable() {
    return disable;
  }

  public void setDisable(boolean disable) {
    this.disable = disable;
  }
  
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "device_type")
//  @JsonIgnoreProperties(value="deviceType")
  private Set<Spec> specs;


  /*
   * Author: Sanero.
   * Created date: Apr 30, 2019
   * Created time: 7:22:08 PM
   * @return the specs
   */
  public Set<Spec> getSpecs() {
    return specs;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 30, 2019
   * Created time: 7:22:08 PM
   * @param specs the specs to set
   */
  public void setSpecs(Set<Spec> specs) {
    this.specs = specs;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:09:15 PM
   * Description: - .
   * @param id
   * @param name
   */
  public DeviceType(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }


  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:09:13 PM
   * Description: - .
   */
  public DeviceType() {
    super();
    // TODO Auto-generated constructor stub
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:09:08 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:09:08 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:09:08 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:09:08 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:09:03 PM
   */
  @Override
  public String toString() {
    return "DeviceType [id=" + id + ", name=" + name + "]";
  }
}
