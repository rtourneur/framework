package com.raf.fwk.security.dao;

import com.raf.fwk.jpa.dao.EntityIdDao;
import com.raf.fwk.security.domain.UserEntity;

/**
 * Dao interface for {@link UserEntity}.
 * 
 * @author RAF
 */
public interface UserEntityDao extends EntityIdDao<UserEntity> {

  /**
   * Find the user whith the username.
   * 
   * @param username
   *          the username
   * @return the user if found, <code>null</code> otherwise
   */
  UserEntity findByUsername(String username);
}
