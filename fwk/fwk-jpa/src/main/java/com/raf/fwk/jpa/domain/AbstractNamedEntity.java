package com.raf.fwk.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract super class for domain named entities.
 *
 * @author RAF
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractNamedEntity extends AbstractEntity implements DomainNamedEntity<String> {

  /** Serial UID. */
  private static final long serialVersionUID = 5184417285823279212L;

  /** The name. */
  @Id
  @Column(name = "NAME", unique = true, nullable = false, length = 30)
  private String name;

  /** The message code for internationalisation. */
  @Column(name = "MESSAGE_CODE", nullable = false, length = 40)
  private String messageCode;

  /**
   * Returns the serializable ID of domain entity.
   *
   * @return the ID
   * @see DomainNamedEntity#getId()
   */
  @Override
  public final String getIdentifier() {
    return getName();
  }

  /**
   * Defines the identifier.
   *
   * @param ident
   *          the identifier to set
   * @see DomainNamedEntity#setIdent(java.io.Serializable)
   */
  @Override
  public final void setIdentifier(final String ident) {
    setName(ident);
  }

  /**
   * Append the properties for the to string builder.
   * 
   * @param builder
   *          the builder
   * @see AbstractEntity#append(ToStringBuilder)
   */
  @Override
  protected final void append(final ToStringBuilder builder) {
    builder.append("name", this.name).append("messageCode", this.messageCode);
    appendNamed(builder);
  }

  /**
   * Append the properties for the to string builder.
   * 
   * @param builder
   *          the builder
   */
  protected void appendNamed(final ToStringBuilder builder) {
    // RAS
  }

}
