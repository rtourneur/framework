package com.raf.fwk.security.domain;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Embeddable class for user roles.
 * 
 * @author RAF
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleUser implements Serializable {

  /** The role USER. */
  public static final RoleUser ROLE_USER = new RoleUser("ROLE_USER");
  /** The role ADMIN. */
  public static final RoleUser ROLE_ADMIN = new RoleUser("ROLE_ADMIN");

  /** Serial UID. */
  private static final long serialVersionUID = 8990175108822524174L;

  /** The role. */
  @Column(name = "ROLE", nullable = false, length = 50)
  private String role;

  /**
   * Return the string representation for this object.
   * 
   * @see Object#toString()
   */
  @Override
  public final String toString() {
    final ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
    builder.append("role", this.role);
    return builder.toString();
  }

}
