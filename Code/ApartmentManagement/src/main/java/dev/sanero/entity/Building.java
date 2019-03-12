/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Building.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 9:32:27 PM
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
 * Created time: 9:32:27 PM
 * Description: TODO - 
 */
@Table(name = "building")
@Entity(name = "building")
public class Building implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "building_id")
  private Set<Floor> floors;

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:41:52 PM
   * @return the floors
   */
  public Set<Floor> getFloors() {
    return floors;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:41:52 PM
   * @param floors the floors to set
   */
  public void setFloors(Set<Floor> floors) {
    this.floors = floors;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:33:13 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:33:13 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:33:13 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:33:13 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:33:25 PM
   * Description: - .
   */
  public Building() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:33:28 PM
   * Description: - .
   * @param id
   * @param name
   */
  public Building(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:33:30 PM
   */
  @Override
  public String toString() {
    return "Building [id=" + id + ", name=" + name + "]";
  }

}
