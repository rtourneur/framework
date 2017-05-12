package com.raf.fwk.service;

import java.io.Serializable;

import com.raf.fwk.jpa.domain.DomainEntity;

/**
 * Super Interface for all services..
 * 
 * @author RAF
 * @param <E>
 *          the interface domain entity.
 * @param <I>
 *          the serializable ID.
 *
 */
public interface EntityService<E extends DomainEntity<I>, I extends Serializable> {

  /**
   * Saves the entity.
   * 
   * @param entity
   *          the entity
   */
  void save(E entity);
}
