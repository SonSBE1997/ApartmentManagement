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

  @ManyToOne
  @JoinColumn(name = "partner_company")
  private PartnerCompany partnerCompany;
  private boolean disable;

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
        + ", price=" + price + ", partnerCompany=" + partnerCompany + "]";
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

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:26 PM
   * @return the partnerCompany
   */
  public PartnerCompany getPartnerCompany() {
    return partnerCompany;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:53:26 PM
   * @param partnerCompany the partnerCompany to set
   */
  public void setPartnerCompany(PartnerCompany partnerCompany) {
    this.partnerCompany = partnerCompany;
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
  public ServiceType(int id, String name, String unit, double price,
      PartnerCompany partnerCompany) {
    super();
    this.id = id;
    this.name = name;
    this.unit = unit;
    this.price = price;
    this.partnerCompany = partnerCompany;
  }

}
