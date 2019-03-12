/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.entity
 * File name: MaintenancePesonal.java
 * Author: Sanero.
 * Created date: Mar 10, 2019
 * Created time: 11:32:02 PM
 */

package dev.sanero.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
 * Created time: 11:32:02 PM
 * Description: TODO - 
 */
@Entity(name = "maintenance_personnel")
@Table(name = "maintenance_personnel")
public class MaintenancePesonal implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "is_supervisor")
  private boolean isSupervisor;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "maintenance_id")
  private Maintenance maintenance;

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:51 PM
   * Description: - .
   */
  public MaintenancePesonal() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:53 PM
   * Description: - .
   * @param id
   * @param isSupervisor
   * @param employee
   * @param maintenance
   */
  public MaintenancePesonal(int id, boolean isSupervisor, Employee employee,
      Maintenance maintenance) {
    super();
    this.id = id;
    this.isSupervisor = isSupervisor;
    this.employee = employee;
    this.maintenance = maintenance;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:54 PM
   */
  @Override
  public String toString() {
    return "MaintenancePesonal [id=" + id + ", isSupervisor=" + isSupervisor
        + ", employee=" + employee + ", maintenance=" + maintenance + "]";
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:59 PM
   * @return the id
   */
  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:59 PM
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:59 PM
   * @return the isSupervisor
   */
  public boolean isSupervisor() {
    return isSupervisor;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:59 PM
   * @param isSupervisor the isSupervisor to set
   */
  public void setSupervisor(boolean isSupervisor) {
    this.isSupervisor = isSupervisor;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:59 PM
   * @return the employee
   */
  public Employee getEmployee() {
    return employee;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:59 PM
   * @param employee the employee to set
   */
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:59 PM
   * @return the maintenance
   */
  public Maintenance getMaintenance() {
    return maintenance;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 10, 2019
   * Created time: 11:33:59 PM
   * @param maintenance the maintenance to set
   */
  public void setMaintenance(Maintenance maintenance) {
    this.maintenance = maintenance;
  }

}
