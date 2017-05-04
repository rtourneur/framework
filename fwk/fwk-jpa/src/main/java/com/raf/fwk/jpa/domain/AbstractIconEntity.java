package com.raf.fwk.jpa.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract super class for domain entities with name and icon.
 *
 * @author RAF
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractIconEntity extends AbstractNamedEntity implements DomainIconEntity<String> {

  /** Serial UID. */
  private static final long serialVersionUID = 1L;

  /** The icon. */
  @Column(name = "ICON", nullable = false, length = 30)
  private String icon;

  /**
   * Append the properties for the to string builder.
   * 
   * @param builder
   *          the builder
   * @see AbstractEntity#append(ToStringBuilder)
   */
  @Override
  protected final void appendNamed(final ToStringBuilder builder) {
    appendIcon(builder);
    builder.append("icon", this.icon);
  }

  /**
   * Append the properties for the to string builder.
   * 
   * @param builder
   *          the builder
   */
  protected void appendIcon(final ToStringBuilder builder) {
    // RAS
  }

}
