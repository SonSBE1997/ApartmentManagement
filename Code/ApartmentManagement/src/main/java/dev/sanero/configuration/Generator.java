package dev.sanero.configuration;

import java.util.Date;

import org.springframework.stereotype.Component;

import dev.sanero.entity.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Generator {
  private String secret = "secret";

  public String generator(Employee employee) {
    // set user
    Claims claims = Jwts.claims().setSubject(employee.getUsername());
    claims.put("employeeId", String.valueOf(employee.getId()));
    claims.put("role", employee.getRole());
    claims.put("name", employee.getName());
    claims.put("dept", employee.getDept().getName());
    return Jwts.builder().setClaims(claims)
        .setExpiration(createExpirationDate())
        .signWith(SignatureAlgorithm.HS512, generateShareSecret()).compact();

  }

  private Date createExpirationDate() {
    return new Date(System.currentTimeMillis() + 86000000);
  }

  private byte[] generateShareSecret() {
    byte[] sharedSecret = new byte[32];
    sharedSecret = secret.getBytes();
    return sharedSecret;
  }
}
