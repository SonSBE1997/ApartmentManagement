/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Room.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 9:42:29 PM
 */

package dev.sanero.entity;

import java.io.Serializable;
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
 * Created time: 9:42:29 PM
 * Description: TODO - 
 */
@Table(name = "room")
@Entity(name = "room")
public class Room implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private double area;
  private String status;
  @Column(name="room_type")
  private String roomType;
  
  @ManyToOne()
  @JoinColumn(name = "floor_id")
  @JsonIgnoreProperties("rooms")
  private Floor floor;

  @ManyToOne()
  @JoinColumn(name = "building_id")
  @JsonIgnoreProperties("floors")
  private Building building;

  /*
   * Author: Sanero.
   * Created date: Apr 6, 2019
   * Created time: 3:05:07 PM
   * @return the roomType
   */
  public String getRoomType() {
    return roomType;
  }

  /*
   * Author: Sanero.
   * Created date: Apr 6, 2019
   * Created time: 3:05:07 PM
   * @param roomType the roomType to set
   */
  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "room_id")
  @JsonIgnoreProperties(value = "room", allowSetters = true)
  private Set<HouseHold> households;
  private boolean disable;

  public boolean isDisable() {
    return disable;
  }

  public void setDisable(boolean disable) {
    this.disable = disable;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:02:48 PM
   * @return the households
   */
  public Set<HouseHold> getHouseholds() {
    return households;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:02:48 PM
   * @param households the households to set
   */
  public void setHouseholds(Set<HouseHold> households) {
    this.households = households;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @return the area
   */
  public double getArea() {
    return area;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @param area the area to set
   */
  public void setArea(double area) {
    this.area = area;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @return the floor
   */
  public Floor getFloor() {
    return floor;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @param floor the floor to set
   */
  public void setFloor(Floor floor) {
    this.floor = floor;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @return the building
   */
  public Building getBuilding() {
    return building;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:47 PM
   * @param building the building to set
   */
  public void setBuilding(Building building) {
    this.building = building;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:57 PM
   * Description: - .
   * @param id
   * @param name
   * @param area
   * @param status
   * @param floor
   * @param building
   */
  public Room(int id, String name, double area, String status, Floor floor,
      Building building) {
    super();
    this.id = id;
    this.name = name;
    this.area = area;
    this.status = status;
    this.floor = floor;
    this.building = building;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:55 PM
   * Description: - .
   */
  public Room() {
    super();
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:47:53 PM
   */
  @Override
  public String toString() {
    return "Room [id=" + id + ", name=" + name + ", area=" + area + ", status="
        + status + ", floor=" + floor + ", building=" + building + "]";
  }

}
