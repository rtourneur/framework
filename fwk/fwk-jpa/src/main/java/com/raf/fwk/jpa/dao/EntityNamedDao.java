package com.raf.fwk.jpa.dao;

import com.raf.fwk.jpa.domain.DomainNamedEntity;

/**
 * Super Interface for all DAOs with String Id.
 *
 * @author RAF
 * @param <E>
 *          the interface domain entity.
 */
public interface EntityNamedDao<E extends DomainNamedEntity<String>> extends EntityDao<E, String> {
  // RAS
}
