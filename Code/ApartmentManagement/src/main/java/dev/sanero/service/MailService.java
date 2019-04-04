/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.service
 * File name: MailService.java
 * Author: Sanero.
 * Created date: Apr 2, 2019
 * Created time: 8:44:49 PM
 */

package dev.sanero.service;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {
  @Autowired
  JavaMailSender mailSender;

  static String emailToRecipient, emailContent;

  public boolean sendMail(String mailTo, String content) {
    try {
      emailToRecipient = mailTo;
      emailContent = content;
      mailSender.send(new MimeMessagePreparator() {
        public void prepare(MimeMessage mimeMessage) throws Exception {
          MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
              true, "UTF-8");
          messageHelper.setTo(emailToRecipient);
          messageHelper.setFrom("sonsbe1997@gmail.com");
          messageHelper
              .setSubject("Đăng ký tài khoản quản lý chung cư thành công");
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
}