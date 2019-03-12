/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: Dept.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 9:14:39 PM
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
 * Created time: 9:14:39 PM
 * Description: TODO - 
 */
@Table(name = "dept")
@Entity(name = "dept")
public class Dept implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:17:07 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:17:07 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:17:07 PM
   * @return the name
   */
  public String getName() {
    return name;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:17:07 PM
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:17:36 PM
   */
  @Override
  public String toString() {
    return "Dept [id=" + id + ", name=" + name + "]";
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:17:33 PM
   * Description: - .
   */
  public Dept() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 9:17:30 PM
   * Description: - .
   * @param id
   * @param name
   */
  public Dept(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

}
