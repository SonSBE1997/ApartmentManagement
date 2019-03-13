package dev.sanero.configuration;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author User
 *
 */
@SuppressWarnings("serial")
public class CustomEmployeeDetails implements UserDetails {

  private String username;
  private String token;
  private int id;
  private String name;
  public Collection<? extends GrantedAuthority> authorities;

  public String getToken() {
    return token;
  }

  public int getId() {
    return id;
  }

  /*
   * Author: Sanero.
   * Created date: Mar 14, 2019
   * Created time: 12:31:48 AM
   * @return the name
   */
  public String getName() {
    return name;
  }

  public CustomEmployeeDetails(String username, String token, int userId,
      String name, Collection<? extends GrantedAuthority> authorities) {
    super();
    this.username = username;
    this.token = token;
    this.id = userId;
    this.name = name;
    this.authorities = authorities;
  }

  /* (non-Javadoc)
   * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return this.authorities;
  }

  /* (non-Javadoc)
   * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
   */
  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
   */
  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return this.username;
  }

  /* (non-Javadoc)
   * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
   */
  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  /* (non-Javadoc)
   * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
   */
  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  /* (non-Javadoc)
   * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
   */
  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  /* (non-Javadoc)
   * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
   */
  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }

}
