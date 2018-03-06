package com.raf.fwk.security.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.raf.fwk.security.dao.UserEntityDao;
import com.raf.fwk.security.domain.RoleUser;
import com.raf.fwk.security.domain.UserEntity;
import com.raf.fwk.security.service.UserEntityService;
import com.raf.fwk.service.AbstractService;
import com.raf.fwk.util.aop.Loggable;

import lombok.NoArgsConstructor;

/**
 * Service implementation for {@link UserDetailsService}.
 * 
 * @author RAF
 */
@Service
@NoArgsConstructor
public final class UserDetailsServiceImpl extends AbstractService<UserEntityDao, UserEntity, Integer>
    implements UserDetailsService, UserEntityService {

  /** The password encoder. */
  @Resource
  private transient BCryptPasswordEncoder passwordEncoder;

  /**
   * Retrieve the user based on the username.
   * 
   * @param username
   *          the username
   * @return the user details
   * @throws UsernameNotFoundException
   *           when the user is not found with the username
   * 
   * @see UserDetailsService#loadUserByUsername(String)
   */
  @Override
  @Loggable
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final List<UserEntity> userEntities = getEntityDao().findByUsername(username);
    if (userEntities.isEmpty()) {
      throw new UsernameNotFoundException("Username not found " + username);
    }
    if (userEntities.size() > 1) {
      throw new UsernameNotFoundException("Too many username found " + username);
    }
    final UserEntity userEntity = userEntities.get(0);
    final Set<GrantedAuthority> authorities = new HashSet<>();
    for (final RoleUser role : userEntity.getRoles()) {
      authorities.add(createAuthority(role));
    }
    return new User(userEntity.getUsername(), userEntity.getPassword(),
        authorities);
  }

  /**
   * Find the user whith the username.
   * 
   * @param username
   *          the username
   * @return the user if found, <code>null</code> otherwise
   * @see UserEntityService#findByUsername(String)
   */
  @Override
  @Loggable
  public UserEntity findByUsername(final String username) {
    UserEntity userEntity = null;
    final List<UserEntity> userEntities = getEntityDao().findByUsername(username);
    if (userEntities.size() == 1) {
      userEntity = userEntities.get(0);
    }
    return userEntity;
  }

  /**
   * Find the user whith the mail adress.
   * 
   * @param mail
   *          the mail adress
   * @return the user if found, <code>null</code> otherwise
   * @see UserEntityService#findByMail(String)
   */
  @Override
  @Loggable
  public UserEntity findByMail(final String mail) {
    UserEntity userEntity = null;
    final List<UserEntity> userEntities = getEntityDao().findByMail(mail);
    if (userEntities.size() == 1) {
      userEntity = userEntities.get(0);
    }
    return userEntity;
  }

  /**
   * Encode the password.
   * 
   * @param entity
   *          the entity
   * @see AbstractService#preSave(com.raf.fwk.jpa.domain.DomainEntity)
   */
  @Override
  protected void preSave(final UserEntity entity) {
    entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
  }

  /**
   * Create the authority with the role.
   * 
   * @param role
   *          the role
   * @return the authority
   */
  private SimpleGrantedAuthority createAuthority(final RoleUser role) {
    return new SimpleGrantedAuthority(role.getRole());
  }

}
