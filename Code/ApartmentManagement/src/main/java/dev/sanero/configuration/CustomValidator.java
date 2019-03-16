package dev.sanero.configuration;

import java.util.Date;

import org.springframework.stereotype.Component;

import dev.sanero.entity.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author User
 *
 */
@Component
public class CustomValidator {

  private String secret = "secret";

  private byte[] generateShareSecret() {
    // Generate 256-bit (32-byte) shared secret
    byte[] sharedSecret = new byte[32];
    sharedSecret = secret.getBytes();
    return sharedSecret;
  }

  //
  public Employee validate(String token) {
    Employee customEmployeeDetail = null;

    try {
      Claims body = Jwts.parser().setSigningKey(generateShareSecret())
          .parseClaimsJws(token).getBody();

      if (isTokenExpired(body.getExpiration())) {
        customEmployeeDetail = new Employee();
        customEmployeeDetail.setUsername(body.getSubject());
        customEmployeeDetail
            .setId(Integer.parseInt((String) body.get("employeeId")));
        customEmployeeDetail.setRole((String) body.get("role"));
        customEmployeeDetail.setName((String) body.get("name"));
      }
      return customEmployeeDetail;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }

  }

  private boolean isTokenExpired(Date tokenDate) {
    return tokenDate.after(new Date());
  }
}
