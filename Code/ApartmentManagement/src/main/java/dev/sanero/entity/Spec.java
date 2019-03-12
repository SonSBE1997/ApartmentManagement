/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Spec.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:10:10 PM
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
 * Created time: 11:10:10 PM
 * Description: TODO - 
 */
@Entity(name = "spec")
@Table(name = "spec")
public class Spec implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  /*
   * Author: Sanero.
   * 
   * Created date: Mar 10, 2019
   * Created time: 11:10:42 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:10:52 PM
   * Description: - .
   */
  public Spec() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:10:51 PM
   * Description: - .
   * @param id
   * @param name
   */
  public Spec(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:10:42 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:10:42 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:10:42 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:10:47 PM
   */
  @Override
  public String toString() {
    return "Spec [id=" + id + ", name=" + name + "]";
  }
}
