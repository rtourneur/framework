package com.raf.fwk.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.raf.fwk.jpa.dao.EntityDao;
import com.raf.fwk.jpa.domain.DomainEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Abstact class for all service implementation.
 * 
 * @author RAF
 * @param <D>
 *          The DAO
 * @param <E>
 *          The entity
 * @param <I>
 *          The identifier
 */
@Getter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractService<D extends EntityDao<E, I>, E extends DomainEntity<I>, I extends Serializable> {

  /** The entity Dao. */
  @Autowired
  private D entityDao;
}
