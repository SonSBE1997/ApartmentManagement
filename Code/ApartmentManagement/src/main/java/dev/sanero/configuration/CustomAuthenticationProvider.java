package dev.sanero.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import dev.sanero.entity.Employee;

@Component
public class CustomAuthenticationProvider
    extends AbstractUserDetailsAuthenticationProvider {

  @Autowired
  private CustomValidator validator;

//	@Override
//	public boolean supports(Class<?> aClass) {
//		// TODO Auto-generated method stub
//		return (AuthenticationToken.class.isAssignableFrom(aClass));
//	}

  @SuppressWarnings("serial")
  @Override
  protected UserDetails retrieveUser(String username,
      UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {

    AuthenticationToken authenticationToken = (AuthenticationToken) authentication;

    String token = authenticationToken.getToken();

    Employee s = validator.validate(token);
    if (s == null) {
      throw new HttpStatusCodeException(HttpStatus.FORBIDDEN,
          "Incorrect or expired!") {
      };
    }
    List<GrantedAuthority> roles = AuthorityUtils
        .commaSeparatedStringToAuthorityList(s.getRole());
    return new CustomEmployeeDetails(s.getEmail(), token, s.getId(), roles);

  }

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    // TODO Auto-generated method stub

  }

}
