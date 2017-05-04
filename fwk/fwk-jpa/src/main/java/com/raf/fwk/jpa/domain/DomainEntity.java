package com.raf.fwk.jpa.domain;

import java.io.Serializable;

/**
 * Interface for Domain entities.
 *
 * @param <I>
 *          the serializable ID.
 * @author RAF
 */
public interface DomainEntity<I extends Serializable> extends Serializable {

  /**
   * Returns the serializable ID of domain entity.
   *
   * @return the ID
   */
  I getIdentifier();

  /**
   * Defines the identifier.
   *
   * @param identifier
   *          the identifier to set
   */
  void setIdentifier(I identifier);
}
