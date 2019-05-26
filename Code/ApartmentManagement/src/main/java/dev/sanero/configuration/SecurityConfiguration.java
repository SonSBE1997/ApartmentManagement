package dev.sanero.configuration;

import java.util.Collections;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
    implements WebMvcConfigurer {
  @Autowired
  private CustomAuthenticationProvider authenticationProvider;

  @Autowired
  private CustomAuthenticationEntryPoint entrypoint;

  @Autowired
  private CustomAccessDenied denied;

  @Bean
  protected AuthenticationManager authenticationManager() {
    return new ProviderManager(
        Collections.singletonList(authenticationProvider));
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {

    registry.addMapping("/**")
        .allowedOrigins("http://localhost:4200", "http://localhost",
            "http://apartment.test", "http://apartment.com")
        .allowedHeaders("*").allowedMethods("*")
        .exposedHeaders("token", "Content-Type").allowCredentials(false)
        .maxAge(3600);

  }

  @Bean
  public AuthenticationTokenFilter authenticationTokenFilter() {
    AuthenticationTokenFilter filter = new AuthenticationTokenFilter();
    filter.setAuthenticationManager(authenticationManager());
    filter.setAuthenticationSuccessHandler(new CustomSuccessHandler());
    return filter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers("/login/**", "/file/**").permitAll().and()
        .authorizeRequests()
        .antMatchers("**/building/**", "**/floor/**", "**/room/**",
            "**/household/**", "**/user/**", "**/employee/**", "**/dept/**",
            "**/card/**", "**/vehicle/**", "**/device/**", "**/maintenance/**",
            "**/service/**")
        .access("hasRole('Manager')").and().exceptionHandling()
        .authenticationEntryPoint(entrypoint).accessDeniedHandler(denied).and()
        .cors().and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(authenticationTokenFilter(),
        UsernamePasswordAuthenticationFilter.class);

    http.headers().cacheControl();
  }

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
    mailSender.setUsername("sonsbe1997@gmail.com");
    mailSender.setPassword("32142135123");
    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");
    return mailSender;
  }
}
