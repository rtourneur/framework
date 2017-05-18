package com.raf.fwk.security.dao;

import java.util.List;

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
   * @return the list of found users
   */
  List<UserEntity> findByUsername(String username);

  /**
   * Find the user whith the mail adress.
   * 
   * @param mail
   *          the mail adress
   * @return the list of found users
   */
  List<UserEntity> findByMail(String mail);
}
