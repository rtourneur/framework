package com.raf.fwk.jpa.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract super class for domain entities with technical Id.
 *
 * @author RAF
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractIdEntity extends AbstractEntity implements DomainIdEntity {

  /** Serial UID. */
  private static final long serialVersionUID = 5184417285823279212L;

  /** The identifier. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Integer ident;

  /**
   * Returns the serializable ID of domain entity.
   *
   * @return the ID
   * @see DomainEntity#getIdentifier()
   */
  @Override
  public final Integer getIdentifier() {
    return getIdent();
  }

  /**
   * Defines the identifier.
   *
   * @param identifier
   *          the identifier to set
   * @see DomainEntity#setIdentifier(java.io.Serializable)
   */
  @Override
  public final void setIdentifier(final Integer identifier) {
    setIdent(identifier);
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
    builder.append("Ident", this.ident);
    appendId(builder);
  }

  /**
   * Append the properties for the to string builder.
   * 
   * @param builder
   *          the builder
   */
  protected void appendId(final ToStringBuilder builder) {
    // RAS
  }

}
