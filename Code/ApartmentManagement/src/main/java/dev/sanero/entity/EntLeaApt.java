/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: EntLeaApt.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 10:21:05 PM
 */

package dev.sanero.entity;

import java.io.Serializable;
import java.sql.Date;

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
 * Created time: 10:21:05 PM
 * Description: TODO - enter leave apartment.
 */
@Table(name = "entLeaApt")
@Entity(name = "entLeaApt")
public class EntLeaApt implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Date times;
  private String type;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private User user;

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:37 PM
   */
  @Override
  public String toString() {
    return "EntLeaApt [id=" + id + ", times=" + times + ", type=" + type
        + ", user=" + user + "]";
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:39 PM
   * Description: - .
   * @param id
   * @param times
   * @param type
   * @param user
   */
  public EntLeaApt(int id, Date times, String type, User user) {
    super();
    this.id = id;
    this.times = times;
    this.type = type;
    this.user = user;
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:40 PM
   * Description: - .
   */
  public EntLeaApt() {
    super();
    // TODO Auto-generated constructor stub
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:45 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:45 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:45 PM
   * @return the times
   */
  public Date getTimes() {
    return times;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:45 PM
   * @param times the times to set
   */
  public void setTimes(Date times) {
    this.times = times;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:45 PM
   * @return the type
   */
  public String getType() {
    return type;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:45 PM
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:45 PM
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 10:23:45 PM
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

}
