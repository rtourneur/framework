package com.raf.fwk.security.domain;

import java.util.Set;

import com.raf.fwk.jpa.domain.DomainIdEntity;

/**
 * Interface for user entity table.
 * 
 * @author RAF
 */
public interface UserEntity extends DomainIdEntity {

  /**
   * Return the username.
   * 
   * @return the username
   */
  String getUsername();

  /**
   * Define the username.
   * 
   * @param username
   *          the username
   */
  void setUsername(String username);

  /**
   * Return the password.
   * 
   * @return the password
   */
  String getPassword();

  /**
   * Define the password.
   * 
   * @param password
   *          the password
   */
  void setPassword(String password);

  /**
   * Return the enabled indicator.
   * 
   * @return the enabled indicator
   */
  boolean isEnabled();

  /**
   * Define the enabled indicator.
   * 
   * @param enabled
   *          the enabled indicator
   */
  void setEnabled(boolean enabled);

  /**
   * Return the mail adress.
   * 
   * @return the mail adress
   */
  String getMail();

  /**
   * Define the mail adress.
   * 
   * @param mail
   *          the mail adress
   */
  void setMail(String mail);

  /**
   * Return the set of roles for the user.
   * 
   * @return the set of roles for the user
   */
  Set<RoleUser> getRoles();

  /**
   * Define the set of roles for the user.
   * 
   * @param roles
   *          the set of roles for the user
   */
  void setRoles(Set<RoleUser> roles);
}
