package dev.sanero.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.sanero.configuration.Generator;
import dev.sanero.entity.Employee;
import dev.sanero.service.LoginService;

//@CrossOrigin(origins="http://localhost:4200", maxAge=3600)
@RestController
public class LoginController {
  @Autowired
  LoginService loginService;

  @Autowired
  Generator generator;

  @PostMapping(value = "/login")
  public ResponseEntity<Employee> login(@RequestBody Employee employee,
      HttpServletResponse response) {
    System.out.println(employee);
    Employee employeeAuthen = loginService.checkLogin(employee.getUsername(),
        employee.getPassword());
    String token = "";
    if (employeeAuthen != null) {
      token = generator.generator(employeeAuthen);
      System.out.println(token);
      response.setHeader("token", token);
      return new ResponseEntity<>(employeeAuthen, HttpStatus.OK);
    } else
      return new ResponseEntity<>(null, HttpStatus.OK);
  }
}
