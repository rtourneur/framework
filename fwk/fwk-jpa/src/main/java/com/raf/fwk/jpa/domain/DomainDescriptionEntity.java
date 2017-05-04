package com.raf.fwk.jpa.domain;

import java.io.Serializable;

/**
 * Interface for Domain entities.
 *
 * @param <I>
 *          the serializable ID.
 * @author RAF
 */
public interface DomainDescriptionEntity<I extends Serializable> extends DomainNamedEntity<I> {

  /**
   * Return the description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Define the description.
   *
   * @param description
   *          the description to set
   */
  void setDescription(String description);

}
