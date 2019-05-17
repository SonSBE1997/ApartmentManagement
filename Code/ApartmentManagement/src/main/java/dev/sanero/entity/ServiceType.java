/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: ServiceType.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:51:46 PM
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
 * Created time: 10:51:46 PM
 * Description: TODO - 
 */
@Entity(name = "service_type")
@Table(name = "service_type")
public class ServiceType implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String unit;
  private double price;

  private String supplier;
  private boolean disable;
  
  private String increase;
  
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "service_type")
  private Set<Service> services;
  
  
  /*
   * Author: Sanero.
   * Created date: May 15, 2019
   * Created time: 9:21:43 PM
   * @return the increase
   */
  public String getIncrease() {
    return increase;
  }

  /*
   * Author: Sanero.
   * Created date: May 15, 2019
   * Created time: 9:21:43 PM
   * @param increase the increase to set
   */
  public void setIncrease(String increase) {
    this.increase = increase;
  }

  /*
   * Author: Sanero.
   * Created date: May 11, 2019
   * Created time: 2:54:05 PM
   * @return the services
   */
  public Set<Service> getServices() {
    return services;
  }

  /*
   * Author: Sanero.
   * Created date: May 11, 2019
   * Created time: 2:54:05 PM
   * @param services the services to set
   */
  public void setServices(Set<Service> services) {
    this.services = services;
  }

  /*
   * Author: Sanero.
   * Created date: May 11, 2019
   * Created time: 1:22:17 PM
   * @return the supplier
   */
  public String getSupplier() {
    return supplier;
  }

  /*
   * Author: Sanero.
   * Created date: May 11, 2019
   * Created time: 1:22:17 PM
   * @param supplier the supplier to set
   */
  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  public boolean isDisable() {
    return disable;
  }

  public void setDisable(boolean disable) {
    this.disable = disable;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:19 PM
   */
  @Override
  public String toString() {
    return "ServiceType [id=" + id + ", name=" + name + ", unit=" + unit
        + ", price=" + price + "]";
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:26 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:26 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:26 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:26 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:26 PM
   * @return the unit
   */
  public String getUnit() {
    return unit;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:26 PM
   * @param unit the unit to set
   */
  public void setUnit(String unit) {
    this.unit = unit;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:26 PM
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:26 PM
   * @param price the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:27 PM
   * Description: - .
   */
  public ServiceType() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:29 PM
   * Description: - .
   * @param id
   * @param name
   * @param unit
   * @param price
   * @param partnerCompany
   */
  public ServiceType(int id, String name, String unit, double price) {
    super();
    this.id = id;
    this.name = name;
    this.unit = unit;
    this.price = price;
  }

}
