package com.raf.fwk.security.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.raf.fwk.security.dao.UserEntityDao;
import com.raf.fwk.security.domain.RoleUser;
import com.raf.fwk.security.domain.UserEntity;
import com.raf.fwk.service.AbstractService;
import com.raf.fwk.util.aop.Loggable;

import lombok.NoArgsConstructor;

/**
 * Service implementation for {@link UserDetailsService}.
 * 
 * @author RAF
 */
@NoArgsConstructor
public final class UserDetailsServiceImpl extends AbstractService<UserEntityDao, UserEntity, Integer>
    implements UserDetailsService {

  /** The password encoder. */
  @Autowired
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
    try {
      final UserEntity userEntity = getEntityDao().findByUsername(username);
      final Set<GrantedAuthority> authorities = new HashSet<>();
      for (final RoleUser role : userEntity.getRoles()) {
        authorities.add(createAuthority(role));
      }
      return new User(userEntity.getUsername(), userEntity.getPassword(),
          authorities);
    } catch (NoResultException e) {
      throw new UsernameNotFoundException("Username not found " + username, e);
    } catch (NonUniqueResultException e) {
      throw new UsernameNotFoundException("Too many username found " + username, e);
    }
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
