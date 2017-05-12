package com.raf.fwk.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
  @Autowired
  private transient AuthenticationManager authenticationManager;

  /** The user detail service. */
  @Autowired
  private transient UserDetailsService userDetailsService;

  /**
   * Retrieves the username of the logged user.
   * 
   * @return the username
   * @see SecurityService#findLoggedInUsername()
   */
  @Override
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
