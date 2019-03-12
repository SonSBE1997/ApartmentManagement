/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: VerhicleType.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:35:40 PM
 */

package dev.sanero.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:35:40 PM
 * Description: TODO - 
 */
@Table(name = "verhicle_type")
@Entity(name = "verhicle_type")
public class VehicleType implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:36:21 PM
   */
  @Override
  public String toString() {
    return "VerhicleType [id=" + id + ", name=" + name + "]";
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:36:29 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:36:29 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:36:29 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:36:29 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:36:31 PM
   * Description: - .
   */
  public VehicleType() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:36:33 PM
   * Description: - .
   * @param id
   * @param name
   */
  public VehicleType(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }
}
