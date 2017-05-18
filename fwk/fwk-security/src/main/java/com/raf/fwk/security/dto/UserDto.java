package com.raf.fwk.security.dto;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO class for user.
 * 
 * @author RAF
 */
@Getter
@Setter
@NoArgsConstructor
public final class UserDto implements Serializable {

  /** Serial UID. */
  private static final long serialVersionUID = 5054503932659543557L;

  /** The username. */
  private String username;

  /** The password. */
  private String password;

  /** The password confirmation. */
  private String passwordConfirm;

  /** The mail adress. */
  private String mail;

  /**
   * Generate the toString.
   *
   * @see Object#toString()
   */
  @Override
  public String toString() {
    final ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
    builder.append("username", this.username).append("password", this.password)
        .append("passwordConfirm", this.passwordConfirm).append("mail", this.mail);
    return builder.toString();
  }

}
