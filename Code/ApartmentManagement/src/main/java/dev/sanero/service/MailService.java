/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: MailService.java
 * Author: Sanero.
 * Created date: Apr 2, 2019
 * Created time: 8:44:49 PM
 */

package dev.sanero.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import dev.sanero.entity.Room;
import dev.sanero.entity.ServiceType;

@Service
public class MailService {
  @Autowired
  JavaMailSender mailSender;

  static String emailToRecipient, emailContent;
  
  Properties prop = new Properties();
  Session session;
  
  public void authorization() {
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.transport.protocol", "smtp");
    prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    session = Session.getInstance(prop, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("sonsbe1997@gmail.com", "32142135123");
        }
    });
  }
  
  public boolean send(String mailTo, String content, String subject) {
    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("sonsbe1997@gmail.com"));
      message.setRecipients(
        Message.RecipientType.TO, InternetAddress.parse(mailTo));
      message.setSubject(subject);
       
      MimeBodyPart mimeBodyPart = new MimeBodyPart();
      mimeBodyPart.setContent(content, "text/html; charset=UTF-8");
       
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mimeBodyPart);
       
      message.setContent(multipart);
       
      Transport.send(message);
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public boolean sendMail(String mailTo, String content, String subject) {
    try {
      emailToRecipient = mailTo;
      emailContent = content;
      mailSender.send(new MimeMessagePreparator() {
        public void prepare(MimeMessage mimeMessage) throws Exception {
          MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
              true, "UTF-8");
          messageHelper.setTo(emailToRecipient);
          messageHelper.setFrom("sonsbe1997@gmail.com");
          messageHelper.setSubject(subject);
          messageHelper.setText(emailContent, true);
        }
      });
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String genContentMail(String name, String username, String password) {
    StringBuilder builder = new StringBuilder();
    builder.append(
        "<h2 style=\"text-align: center; color: #0ca118; margin-top: 30px;\">Đăng ký thành công</h2>");
    builder.append(
        "<p style=\"margin-top:20px;margin-left:50px;\"><span style=\"font-weight: bold;\">Họ và tên:</span> "
            + name + "</p>");
    builder.append(
        "<p style=\"margin-top:20px;margin-left:50px;\"><span style=\"font-weight: bold;\">Tên đăng nhập:</span> "
            + username + "</p>");
    builder.append(
        "<p style=\"margin-top:20px;margin-left:50px;\"><span style=\"font-weight: bold;\">Mật khẩu:</span> "
            + password + "</p>");
    builder.append(
        "<div style=\"text-align: center;\"><a style=\"margin-top:100px;\" target=\"_blank\" href=\"http://localhost:4200/login\">Chuyển đến trang đăng nhập</a></div>");
    return builder.toString();
  }
  
  
  public StringBuilder getInvoiceString(dev.sanero.entity.Service s, String fullName) {
    StringBuilder builder = new StringBuilder();
    builder.append("<div style=\"margin: 40px;\">");
    builder.append("<h2 align=\"center\" style=\"text-transform: uppercase\">Hoá đơn " + s.getServiceType().getName() + "</h2>");
    String date[] = s.getCollectMonth().split("-");
    String dateStr = "20-" + (Integer.parseInt(date[0]) - 1) + "-" + date[1] + " đến 20-" + s.getCollectMonth();
    builder.append("<div><b>Kể từ ngày: </b>" + dateStr + "</div><br />");
    builder.append("<div><b>Nhà cung cấp: </b> " + s.getServiceType().getSupplier() + "</div><br />");
    builder.append("<div><b>Khách hàng: </b> " + fullName + "</div> <br />");
    Room r = s.getRoom();
    String add = r.getName() + " - " + r.getFloor().getName() + " - " + r.getBuilding().getName();
    builder.append("<div><b>Căn hộ: </b> " + add + "</>");
    NumberFormat formatter = new DecimalFormat("#0.00");  
    if (!"".equals(s.getDetail())) {
      String index[] = s.getDetail().split("-");
      double use = Double.parseDouble(index[1]) - Double.parseDouble(index[0]);
      
      List<Double> m = new ArrayList<>();
      List<Double> p = new ArrayList<>();
      List<Double> arr = new ArrayList<>();
      List<Double> arr1 = new ArrayList<>();
      String increase = "";
      if (s.getIncrease() != null) {
        increase = s.getIncrease();
      }
      double price = 0;
      
      ServiceType type = s.getServiceType();
      if (!"".equals(increase)) {
        for (String str : increase.split(";")) {
           String temp[] = str.split(" - ");
           m.add(Double.parseDouble(temp[0]));
           p.add(Double.parseDouble(temp[1]));
        }
        
        double tempo = use;
        int size = m.size();
        for (int i = 0; i < size; i++) {
          if (use > m.get(i)) {
            double t = m.get(i);
            if (i > 0) {
              t -= m.get(i-1);
            }
            double pp = 0;
            if (i < size - 1) {
              tempo -= t;
              pp += t * p.get(i);
              arr.add(t);
            } else {
              pp += tempo * p.get(i);
              arr.add(tempo);
            }
            price += pp;
            arr1.add(pp);
          } else {
            double pp = 0;
            pp += tempo * p.get(i);
            price += pp;
            arr.add(tempo);
            arr1.add(pp);
            break;
          }
        }
      } else {
        price = use * type.getPrice();
      }
      
      builder.append("<table style=\"text-align: center; border-collapse: collapse;margin-top: 50px;width: 100%;\">");
      builder.append("<thead>");
      builder.append("<tr>");
      builder.append("<th style=\"width: 20%;border: 1px solid #000000\">Chỉ số cũ</th>");
      builder.append("<th style=\"width: 20%;border: 1px solid #000000\">Chỉ số mới</th>");
      builder.append("<th style=\"width: 20%;border: 1px solid #000000\">Chỉ số tiêu thụ</th>");
      builder.append("<th style=\"width: 20%;border: 1px solid #000000\">Đơn giá</th>");
      builder.append("<th style=\"width: 20%;border: 1px solid #000000\">Thành tiền</th>");
      builder.append(" </tr>");
      builder.append(" </thead>");
      builder.append("<tbody>");
      builder.append("<tr>");
      builder.append(" <td style=\"border: 1px solid #000000\"> " + index [0] + " </td>");
      builder.append(" <td style=\"border: 1px solid #000000\">" + index [1] + "</td>");
      if(arr.size() == 0) {
        builder.append("<td style=\"border: 1px solid #000000\">" +  formatter.format(type.getPrice()) + "</td>");
        builder.append(" <td style=\"border: 1px solid #000000\"> " + formatter.format(price)  + "</td>");
      } else {
        builder.append("<td style=\"border: 1px solid #000000\"></td>");
        builder.append("<td style=\"border: 1px solid #000000\"></td>");
      }
      builder.append("<td style=\"border: 1px solid #000000\">" + formatter.format(use) + "</td>");
      builder.append(" </tr>");
      
      if (arr.size() > 0) {
        for (int i = 0; i < arr.size(); i++) {
          builder.append("<tr>");
          builder.append("<td style=\"border: 1px solid #000000\"></td>");
          builder.append("<td style=\"border: 1px solid #000000\"></td>");
          
          builder.append(" <td style=\"border: 1px solid #000000\"> " + formatter.format(arr.get(i))  + "</td>");
          builder.append(" <td style=\"border: 1px solid #000000\"> " + formatter.format(p.get(i))  + "</td>");
          builder.append(" <td style=\"border: 1px solid #000000\"> " + formatter.format(arr1.get(i))  + "</td>");
          builder.append(" </tr>");
        }
      }
      
      builder.append("<tr>");
      builder.append("  <td colspan=\"4\" style=\"border: 1px solid #000000\">Thuế GTGT 10%</td>");
      builder.append(" <td style=\"border: 1px solid #000000\">" + formatter.format((price * 0.1)) + "</td>");
      builder.append("</tr>");
      builder.append(" <tr>");
      builder.append("   <td colspan=\"4\" style=\"border: 1px solid #000000\">Tổng cộng tiền thanh toán</td>");
      builder.append("<td style=\"border: 1px solid #000000\">" + formatter.format((price * 1.1)) + "</td>");
      builder.append("</tr>");
      builder.append("</tbody>");
      builder.append("</table>");
    } else {
      builder.append("<div><b>Tiền thanh toán:</b> " + formatter.format(s.getPrice()) + "</div> <br />");
    }
    builder.append("<br />");
    builder.append("<div style=\"padding-right: 30px !important; text-align: right\">Người lập hoá đơn</div>");
    builder.append("<div style=\"padding-right: 50px !important; text-align: right\">"+ s.getEmployee().getName() +"</div>");
    builder.append("</div>");
    
    return builder;
  }
  
  public StringBuilder getInvoiceHeader(dev.sanero.entity.Service s, String fullName) {
    StringBuilder builder = new StringBuilder();
    builder.append("<div style=\"margin: 40px;\">");
    builder.append("<h2 align=\"center\" style=\"text-transform: uppercase\">Thông báo thu tiền tháng ");
    builder.append(s.getCollectMonth());
    builder.append("</h2>");
    String date[] = s.getCollectMonth().split("-");
    String dateStr = "20-" + (Integer.parseInt(date[0]) - 1) + "-" + date[1] + " đến 20-" + s.getCollectMonth();
    builder.append("<div><b>Kể từ ngày: </b>" + dateStr + "</div><br />");
    builder.append("<div><b>Chủ hộ: </b> " + fullName + "</div> <br />");
    Room r = s.getRoom();
    String add = r.getName() + " - " + r.getFloor().getName() + " - " + r.getBuilding().getName();
    builder.append("<div><b>Căn hộ: </b> " + add + "</>");
    builder.append("<table style=\"text-align: center; border-collapse: collapse;margin-top: 50px;width: 100%;\">");
    builder.append("<thead>");
    builder.append("<tr>");
    builder.append("<th style=\"width: 50%;border: 1px solid #000000\">Loại dịch vụ</th>");
    builder.append("<th style=\"width: 50%;border: 1px solid #000000\">Thành tiền</th>");
    builder.append(" </tr>");
    builder.append(" </thead>");
    builder.append("<tbody>");
    return builder;
  }
  
  public StringBuilder getInvoiceBody(dev.sanero.entity.Service s) {
    StringBuilder builder = new StringBuilder();
    builder.append("<tr>");
    builder.append(" <td style=\"border: 1px solid #000000\"> " + s.getServiceType().getName() + " </td>");
    builder.append(" <td style=\"border: 1px solid #000000\">" + s.getPrice() + "</td>");
    builder.append(" </tr>");
    return builder;
  }
  

  public StringBuilder getInvoiceFooter(String empName, double  total) {
    StringBuilder builder = new StringBuilder();
    builder.append("<tr>");
    builder.append("<td style=\"border: 1px solid #000000\">Tổng cộng tiền thanh toán</td>");
    builder.append("<td style=\"border: 1px solid #000000\">" + total + "</td>");
    builder.append("</tr>");
    builder.append("</tbody>");
    builder.append("</table>");
    builder.append("<br />");
    builder.append("<div style=\"padding-right: 30px !important; text-align: right\">Người thông báo</div>");
    builder.append("<div style=\"padding-right: 50px !important; text-align: right\">"+ empName +"</div>");
    builder.append("</div>");
    return builder;
  }
}