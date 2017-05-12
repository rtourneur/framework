package com.raf.fwk.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.raf.fwk.jpa.dao.EntityDao;
import com.raf.fwk.jpa.domain.DomainEntity;
import com.raf.fwk.util.aop.Loggable;

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
public abstract class AbstractService<D extends EntityDao<E, I>, E extends DomainEntity<I>, I extends Serializable>
    implements EntityService<E, I> {

  /** The entity Dao. */
  @Autowired
  private D entityDao;

  /**
   * Saves the entity.
   * 
   * @param entity
   *          the entity
   * @see EntityService#save(DomainEntity)
   */
  @Override
  @Transactional
  @Loggable
  public final void save(final E entity) {
    preSave(entity);
    this.entityDao.persist(entity);
    postSave(entity);
  }

  /**
   * Perform action before saving entity.
   * 
   * @param entity
   *          the entity
   */
  protected void preSave(final E entity) {
    // empty method for overidding.
  }

  /**
   * Perform action after saving entity.
   * 
   * @param entity
   *          the entity
   */
  protected void postSave(final E entity) {
    // empty method for overidding.
  }
}
