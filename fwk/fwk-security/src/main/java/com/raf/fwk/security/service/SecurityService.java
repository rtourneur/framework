package com.raf.fwk.security.service;

/**
 * Interface for Security service.
 * 
 * @author RAF
 */
public interface SecurityService {

  /**
   * Retrieves the username of the logged user.
   * 
   * @return the username
   */
  String findLoggedInUsername();

  /**
   * Log the user.
   * 
   * @param username
   *          the username
   * @param password
   *          the password
   */
  void autologin(String username, String password);

}
