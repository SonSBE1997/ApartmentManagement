/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Floor.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 9:38:28 PM
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 9:38:28 PM
 * Description: TODO - 
 */
@Table(name = "floor")
@Entity(name = "floor")
public class Floor implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  
  @ManyToOne()
  @JoinColumn(name = "building_id")
  @JsonIgnoreProperties("floors")
  private Building building;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "floor_id")
  private Set<Room> rooms;
  private boolean disable;

  public boolean isDisable() {
    return disable;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 21, 2019
   * Created time: 1:06:08 AM
   * @return the building
   */
  public Building getBuilding() {
    return building;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 21, 2019
   * Created time: 1:06:08 AM
   * @param building the building to set
   */
  public void setBuilding(Building building) {
    this.building = building;
  }

  public void setDisable(boolean disable) {
    this.disable = disable;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 13, 2019
   * Created time: 9:03:19 PM
   * @return the rooms
   */
  public Set<Room> getRooms() {
    return rooms;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 13, 2019
   * Created time: 9:03:19 PM
   * @param rooms the rooms to set
   */
  public void setRooms(Set<Room> rooms) {
    this.rooms = rooms;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:40:21 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:40:21 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:40:21 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:40:21 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:40:27 PM
   * Description: - .
   * @param id
   * @param name
   */
  public Floor(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:40:28 PM
   * Description: - .
   */
  public Floor() {
    super();
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:40:30 PM
   */
  @Override
  public String toString() {
    return "Floor [id=" + id + ", name=" + name + "]";
  }
}
