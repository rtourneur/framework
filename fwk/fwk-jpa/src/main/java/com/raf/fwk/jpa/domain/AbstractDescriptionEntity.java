package com.raf.fwk.jpa.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract super class for domain entities.
 * 
 * @author RAF
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractDescriptionEntity extends AbstractNamedEntity implements DomainDescriptionEntity<String> {

  /** Serial UID. */
  private static final long serialVersionUID = -1579839011197310269L;
  
  /** The description. */
  @Column(name = "DESCRIPTION", nullable = false, length = 255)
  private String description;
  
  /**
   * Append the properties for the to string builder.
   * 
   * @param builder the builder
   * @see AbstractNamedEntity#appendNamed(ToStringBuilder)
   */
  @Override
  protected final void appendNamed(final ToStringBuilder builder) {
    builder.append("description", this.description);
    appendDescription(builder);
  }
  
  /**
   * Append the properties for the to string builder.
   * 
   * @param builder the builder
   */
  protected void appendDescription(final ToStringBuilder builder) {
    // RAS
  }
}
