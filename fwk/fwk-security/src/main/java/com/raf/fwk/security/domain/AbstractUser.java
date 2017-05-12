package com.raf.fwk.security.domain;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractIdEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract class for user entity table.
 * 
 * @author RAF
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractUser extends AbstractIdEntity implements UserEntity {

  /** Serial UID. */
  private static final long serialVersionUID = 5054503932659543557L;

  /** The username. */
  @Column(name = "USERNAME", nullable = false, length = 50)
  private String username;

  /** The password. */
  @Column(name = "PASSWORD", nullable = false, length = 50)
  private String password;

  /** The enabled indicator. */
  @Column(name = "ENABLED")
  private boolean enabled;

  /** The mail adress. */
  @Column(name = "MAIL", nullable = false, length = 50)
  private String mail;

  /** The set of roles. */
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "ROLES")
  private Set<RoleUser> roles;

  /**
   * Append the object values for the to string builder.
   * 
   * @param builder
   *          the to string builder
   * 
   * @see AbstractIdEntity#appendId(ToStringBuilder)
   */
  @Override
  protected final void appendId(final ToStringBuilder builder) {
    builder.append("username", this.username).append("password", this.password).append("enabled", this.enabled).append(
        "mail",
        this.mail);
    appendUser(builder);
  }

  /**
   * Append the object values for the to string builder.
   * 
   * @param builder
   *          the to string builder
   */
  protected void appendUser(final ToStringBuilder builder) {
    // Empty method for overriding
  }

}
