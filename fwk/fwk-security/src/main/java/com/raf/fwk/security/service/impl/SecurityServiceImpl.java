package com.raf.fwk.security.service.impl;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.raf.fwk.security.service.SecurityService;
import com.raf.fwk.util.aop.Loggable;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for {@link SecurityService}.
 * 
 * @author RAF
 */
@Service
@NoArgsConstructor
@Slf4j
public final class SecurityServiceImpl implements SecurityService {

  /** The authentication manager. */
  @Resource
  private transient AuthenticationManager authenticationManager;

  /** The user detail service. */
  @Resource
  private transient UserDetailsService userDetailsService;

  /**
   * Retrieves the username of the logged user.
   * 
   * @return the username
   * @see SecurityService#findLoggedInUsername()
   */
  @Override
  @Loggable
  public String findLoggedInUsername() {
    final Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
    String username = null;
    if (userDetails instanceof UserDetails) {
      username = ((UserDetails) userDetails).getUsername();
    }

    return username;
  }

  /**
   * Log the user.
   * 
   * @param username
   *          the username
   * @param password
   *          the password
   * @see SecurityService#autologin(String, String)
   */
  @Override
  @Loggable
  public void autologin(final String username, final String password) {
    final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
    final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        userDetails, password, userDetails.getAuthorities());

    this.authenticationManager.authenticate(authenticationToken);

    if (authenticationToken.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      log.debug("Auto login {} successfully !", username);
    }
  }
}
