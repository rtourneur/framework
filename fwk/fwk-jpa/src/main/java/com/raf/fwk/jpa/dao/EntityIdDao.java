package com.raf.fwk.jpa.dao;

import com.raf.fwk.jpa.domain.DomainIdEntity;

/**
 * Super Interface for all DAOs with technical Id and functionnal identifier.
 *
 * @author RAF
 * @param <E>
 *          the interface domain entity.
 */
public interface EntityIdDao<E extends DomainIdEntity> extends EntityDao<E, Integer> {

  /**
   * Retrieve the entity with it's functionnal identifier.
   *
   * @param example
   *          the example instance with functionnal id
   * @return the entity
   */
  E getByExample(E example);

}
