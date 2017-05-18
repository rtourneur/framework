package com.raf.fwk.security.service;

import com.raf.fwk.security.domain.UserEntity;
import com.raf.fwk.service.EntityService;

/**
 * Interface for {@link UserEntity} service.
 * 
 * @author RAF
 */
public interface UserEntityService extends EntityService<UserEntity, Integer> {

  /**
   * Find the user whith the username.
   * 
   * @param username
   *          the username
   * @return the user if found, <code>null</code> otherwise
   */
  UserEntity findByUsername(String username);

  /**
   * Find the user whith the mail adress.
   * 
   * @param mail
   *          the mail adress
   * @return the user if found, <code>null</code> otherwise
   */
  UserEntity findByMail(String mail);

}
