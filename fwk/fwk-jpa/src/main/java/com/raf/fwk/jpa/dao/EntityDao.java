package com.raf.fwk.jpa.dao;

import java.io.Serializable;
import java.util.List;

import com.raf.fwk.jpa.domain.DomainEntity;
import com.raf.fwk.util.Paged;

/**
 * Super Interface for all DAOs.
 *
 * @author RAF
 * @param <E>
 *          the interface domain entity.
 * @param <I>
 *          the serializable ID.
 */
public interface EntityDao<E extends DomainEntity<I>, I extends Serializable> {

  /**
   * Retrieve the entity with it's identifier.
   *
   * @param ident
   *          the identifier
   * @return the entity
   */
  E getById(I ident);

  /**
   * Insert a new entity.
   *
   * @param entity
   *          the entity to insert
   */
  void persist(E entity);

  /**
   * Updates an existing entity.
   *
   * @param entity
   *          the entity to update
   * @return the entity
   */
  E merge(E entity);

  /**
   * Deletes an existing entity.
   *
   * @param entity
   *          the entity to delete
   */
  void remove(E entity);

  /**
   * Count the corresponding entities from an example entity.
   *
   * @param example
   *          the entity example
   * @return the count
   */
  Long countByExample(E example);

  /**
   * Retrieves a list of entities from an example entity.
   *
   * @param example
   *          the entity example
   * @return a list of entities
   */
  List<E> findByExample(E example);

  /**
   * List the entities.
   * 
   * @return the list of entities
   */
  List<E> listAll();

  /**
   * List the entities.
   * 
   * @param nbRows
   *          the number of rows to return
   * @param pageNum
   *          the page number to return
   * @return a paged list of entities
   */
  Paged<E> list(int nbRows, int pageNum);

  /**
   * List the entities.
   * 
   * @param example
   *          the entity example
   * @param nbRows
   *          the number of rows to return
   * @param pageNum
   *          the page number to return
   * @return a paged list of entities
   */
  Paged<E> list(E example, int nbRows, int pageNum);
}
