package com.raf.fwk.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Spring security config.
 * 
 * @author RAF
 */
@Configuration
@EnableWebSecurity
@NoArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /** The user detail service. */
  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * Define the password encoder.
   * 
   * @return the password encoder
   */
  @Bean
  public BCryptPasswordEncoder bcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Expose the {@link AuthenticationManager}.
   * 
   * @return the AuthenticationManager
   * @see WebSecurityConfigurerAdapter#authenticationManagerBean()
   */
  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * Configure the security.
   * 
   * @param http
   *          the http security
   * @throws Exception
   *           if an error occurs
   * @see WebSecurityConfigurerAdapter#configure(HttpSecurity)
   */
  @Override
  protected final void configure(final HttpSecurity http) throws Exception {
    log.info("Configuring http security");
    http
        .authorizeRequests()
        .antMatchers("/resources/**", "/registration").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll();
  }

  /**
   * Configure the authentication manager.
   * 
   * @param auth
   *          the authentication manager
   * @throws Exception
   *           if an error occurs
   */
  @Autowired
  public final void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userDetailsService).passwordEncoder(bcryptPasswordEncoder());
  }

}
