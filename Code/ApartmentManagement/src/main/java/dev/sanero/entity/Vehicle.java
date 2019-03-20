/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Vehicle.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:36:56 PM
 */

package dev.sanero.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * @author Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:36:56 PM
 * Description: TODO - 
 */
@Entity(name = "verhicle")
@Table(name = "verhicle")
public class Vehicle implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "plate_numb")
  private String plateNumber;

  @OneToOne
  @JoinColumn(name = "card_numb")
  private Card card;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "verhicle_type")
  private VehicleType vehicleType;

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:41:31 PM
   */
  @Override
  public String toString() {
    return "Vehicle [plateNumber=" + plateNumber + ", card=" + card + ", user="
        + user + ", vehicleType=" + vehicleType + "]";
  }

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
   * Created time: 10:41:38 PM
   * @return the plateNumber
   */
  public String getPlateNumber() {
    return plateNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:41:38 PM
   * @param plateNumber the plateNumber to set
   */
  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:41:38 PM
   * @return the card
   */
  public Card getCard() {
    return card;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:41:38 PM
   * @param card the card to set
   */
  public void setCard(Card card) {
    this.card = card;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:41:38 PM
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:41:38 PM
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:41:38 PM
   * @return the vehicleType
   */
  public VehicleType getVehicleType() {
    return vehicleType;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:41:38 PM
   * @param vehicleType the vehicleType to set
   */
  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:41:40 PM
   * Description: - .
   */
  public Vehicle() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:41:41 PM
   * Description: - .
   * @param plateNumber
   * @param card
   * @param user
   * @param vehicleType
   */
  public Vehicle(String plateNumber, Card card, User user,
      VehicleType vehicleType) {
    super();
    this.plateNumber = plateNumber;
    this.card = card;
    this.user = user;
    this.vehicleType = vehicleType;
  }
}
