/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.repository
 * File name: ServiceRepository.java
 * Author: Sanero.
 * Created date: May 11, 2019
 * Created time: 1:59:27 PM
 */

package dev.sanero.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sanero.entity.Room;
import dev.sanero.entity.Service;

/*
 * @author Sanero.
 * Created date: May 11, 2019
 * Created time: 1:59:27 PM
 * Description: TODO - 
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
  @Query(value="select paid, count(id) from service where collect_month like ?1 and service_type = ?2 group by paid", nativeQuery=true)
  public List<Object> paidByMonthAndType(String month, int type);
  
  @Query(value="select paid, sum(price) from service where collect_month like ?1 and service_type = ?2 group by paid", nativeQuery=true)
  public List<Object> pricePaidByMonthAndType(String month, int type);
  
  @Query(value="select t.name, sum(ifnull(s.price,0)) from service_type t left outer join (select * from service where collect_month like ?1 and paid=?2 ) s\r\n" + 
      "on t.id = s.service_type group by t.name order by t.id", nativeQuery=true)
  public List<Object> pricePaidByMonth(String month, int paid);
  
  @Query(value="select t.name, count(s.id) from service_type t left outer join (select * from service where collect_month like ?1 and paid=?2 ) s\r\n" + 
      "on t.id = s.service_type group by t.name order by t.id", nativeQuery=true)
  public List<Object> paidByMonth(String month, int paid);
  
  @Query(value="select count(id) from service where collect_month like ?1  and service_type = ?2 and payment_date is null", nativeQuery=true)
  public long isGeneratedFixedService(String month, int type);
  
  @Query("select s.room from service s where s.collectMonth like ?1 and s.serviceType.id = ?2 and s.paymentDate is not null")
  public List<Room> listGeneratedFixedService(String month, int type);
  
  @Query("select s from service s where s.collectMonth like ?1 and s.paymentDate is null order by s.room.id")
  public List<Service> listServiceIsNotPay(String month);
  
  @Query(value="select t.name as name, ifnull(sum(ifnull(s.price,0)), 0) as price from service_type t left outer join (select * from service where collect_month like ?1 and paid=?2 ) s\r\n" + 
      "on t.id = s.service_type group by t.name order by t.id", nativeQuery=true)
  public List<Map<String, Object>> pricePaidByMonth1(String month, int paid);
  
  @Query(value="select t.name as name, ifnull(count(s.id), 0) as numRoom from service_type t left outer join (select * from service where collect_month like ?1 and paid=?2 ) s\r\n" + 
      "on t.id = s.service_type group by t.name order by t.id", nativeQuery=true)
  public List<Map<String, Object>> paidByMonth1(String month, int paid);
}
