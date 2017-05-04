package com.raf.fwk.jpa.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.transaction.annotation.Transactional;

import com.raf.fwk.jpa.domain.DomainEntity;
import com.raf.fwk.util.Paged;
import com.raf.fwk.util.PagedImpl;
import com.raf.fwk.util.aop.Loggable;

import lombok.RequiredArgsConstructor;

/**
 * Abstract implementation for all DAOs.
 *
 * @author RAF
 * @param <E>
 *          the interface entity
 * @param <I>
 *          the identifier
 */
@RequiredArgsConstructor
public abstract class AbstractDao<E extends DomainEntity<I>, I extends Serializable> implements EntityDao<E, I> {

  /** The ident field for technical id. */
  protected static final String IDENT = "ident";

  /** The name field for name id. */
  protected static final String NAME = "name";

  /** The persistence context. */
  @PersistenceContext
  private transient EntityManager entityManager;

  /** The entity Class. Must be a class, not an interface. */
  private final transient Class<? extends E> entityClass;

  /**
   * Retrieve the entity with it's identifier.
   *
   * @param ident
   *          the identifier
   * @return the entity
   * @see EntityDao#getById(Serializable)
   */
  @Override
  @Loggable
  public final E getById(final I ident) {
    return this.entityManager.find(this.entityClass, ident);
  }

  /**
   * Insert a new entity.
   *
   * @param entity
   *          the entity to insert
   * @see EntityDao#persist(DomainEntity)
   */
  @Transactional
  @Override
  @Loggable
  public final void persist(final E entity) {
    this.entityManager.persist(entity);
  }

  /**
   * Updates an existing entity.
   *
   * @param entity
   *          the entity to update
   * @return the entity
   * @see EntityDao#merge(DomainEntity)
   */
  @Transactional
  @Override
  @Loggable
  public final E merge(final E entity) {
    return this.entityManager.merge(entity);
  }

  /**
   * Deletes an existing entity.
   *
   * @param entity
   *          the entity to delete
   * @see EntityDao#remove(DomainEntity)
   */
  @Transactional
  @Override
  @Loggable
  public final void remove(final E entity) {
    if (this.entityManager.contains(entity)) {
      this.entityManager.remove(entity);
    } else {
      this.entityManager.remove(merge(entity));
    }

  }

  /**
   * count the entities.
   * 
   * @return the number of entities
   * @see EntityDao#list(int, int)
   */
  @Transactional
  @Override
  @Loggable
  public final Long countByExample(final E example) {
    final CriteriaBuilder builder = getCriteriaBuilder();

    CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
    final Root<E> root = (Root<E>) countCriteria.from(this.entityClass);
    countCriteria = countCriteria.select(builder.count(root));
    if (example != null) {
      countCriteria = countCriteria.where(getPredicates(root, example));
    }
    return this.entityManager.createQuery(countCriteria).getSingleResult();
  }

  /**
   * Retrieves a list of entities from an example entity.
   *
   * @param example
   *          the entity example
   * @return a list of entities
   * @see EntityDao#findByExample(DomainEntity)
   */
  @Transactional
  @Override
  @Loggable
  public final List<E> findByExample(final E example) {
    final CriteriaQuery<E> query = getQuery();
    final Root<E> root = getRoot(query);
    final Predicate[] predicates = getPredicates(root, example);
    final TypedQuery<E> typedQuery = this.entityManager.createQuery(query.select(root).where(predicates));
    return typedQuery.getResultList();
  }

  /**
   * List the entities.
   * 
   * @return the list of entities
   * @see EntityDao#listAll()
   */
  @Transactional
  @Override
  @Loggable
  public final List<E> listAll() {
    final CriteriaQuery<E> query = getQuery();
    final Root<E> root = getRoot(query);
    final TypedQuery<E> typedQuery = this.entityManager.createQuery(query.select(root));
    return typedQuery.getResultList();
  }

  /**
   * List the entities.
   * 
   * @param nbRows
   *          the number of rows to return
   * @param pageNum
   *          the page number to return
   * @return a paged list of entities
   * @see EntityDao#list(int, int)
   */
  @Transactional
  @Override
  @Loggable
  public final Paged<E> list(final int nbRows, final int pageNum) {
    return list(null, nbRows, pageNum);
  }

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
   * @see EntityDao#list(DomainEntity, int, int)
   */
  @Transactional
  @Override
  @Loggable
  public final Paged<E> list(final E example, final int nbRows, final int pageNum) {
    final CriteriaBuilder builder = getCriteriaBuilder();
    CriteriaQuery<E> query = getQuery();
    final Root<E> root = getRoot(query);
    query = query.select(root).orderBy(getOrder(builder, root));
    if (example != null) {
      query = query.where(getPredicates(root, example));
    }
    final TypedQuery<E> typedQuery = this.entityManager.createQuery(query);
    final Paged<E> result = new PagedImpl<>();
    if (nbRows > 0) {
      final Long numRows = countByExample(example);
      result.setNbRows(nbRows);
      result.setPageNum(pageNum);
      result.setTotalRows(numRows.intValue());
      setLimites(typedQuery, result);
    }
    final List<E> pageItems = typedQuery.getResultList();
    result.addAll(pageItems);
    return result;
  }

  /**
   * Returns the criteria builder.
   *
   * @return the criteria builder
   */
  protected final CriteriaBuilder getCriteriaBuilder() {
    return this.entityManager.getCriteriaBuilder();
  }

  /**
   * Builds a query.
   * 
   * @return the query
   */
  protected final CriteriaQuery<E> getQuery() {
    final CriteriaBuilder builder = getCriteriaBuilder();
    return (CriteriaQuery<E>) builder.createQuery(this.entityClass);
  }

  /**
   * Build a typed query.
   *
   * @param query
   *          the criteria query
   * @return the criteria builder
   */
  protected final TypedQuery<E> getTypedQuery(final CriteriaQuery<E> query) {
    return this.entityManager.createQuery(query);
  }

  /**
   * Get the root element from the query.
   * 
   * @param query
   *          the query
   * @return the root element
   */
  protected final <T> Root<E> getRoot(final CriteriaQuery<T> query) {
    return (Root<E>) query.from(this.entityClass);
  }

  /**
   * Retrieves a list of selection with specific predicates.
   *
   * @param query
   *          the query
   * @param root
   *          the selection
   * @param predicates
   *          the array of predicate
   * @return a list of entities
   */
  protected final <T> List<T> find(final CriteriaQuery<T> query, final Selection<T> selection,
      final List<Predicate> predicates) {
    final TypedQuery<T> typedQuery = this.entityManager.createQuery(query.select(selection).where(
        predicates.toArray(new Predicate[predicates.size()])));
    return typedQuery.getResultList();
  }

  /**
   * Creates a "eqauls" predicate.
   *
   * @param from
   *          the root type
   * @param attributeName
   *          the name of the field
   * @param value
   *          the searched value
   * @return the predicate
   */
  protected final Predicate getEquals(final From<E, ?> from, final String attributeName, final Object value) {
    return getCriteriaBuilder().equal(from.get(attributeName), value);
  }

  /**
   * Creates a "eqauls" predicate.
   *
   * @param from
   *          the root type
   * @param embeddableName
   *          the name of the embeddable
   * @param attributeName
   *          the name of the field
   * @param value
   *          the searched value
   * @return the predicate
   */
  protected final Predicate getEquals(final From<E, ?> from, final String embeddableName, final String attributeName,
      final Object value) {
    return getCriteriaBuilder().equal(from.get(embeddableName).get(attributeName), value);
  }

  /**
   * Creates a "between date" predicate.
   *
   * @param from
   *          the root type
   * @param attributeName
   *          the name of the field
   * @param value
   *          the searched value
   * @return the predicate
   */
  protected final Predicate getBetweenDate(final From<E, ?> from, final String attributeName, final Date valueMin,
      final Date valueMax) {
    return getCriteriaBuilder().between(from.<Date> get(attributeName), valueMin, valueMax);
  }

  /**
   * Creates a "not null" predicate.
   * 
   * @param from
   *          the root type
   * @param attributeName
   *          the name of the field
   * @return the predicate
   */
  protected final Predicate getIsNotNull(final From<E, ?> from, final String attributeName) {
    return getCriteriaBuilder().isNotNull(from.get(attributeName));
  }

  /**
   * Creates a "like" predicate.
   *
   * @param root
   *          the root type
   * @param embeddableName
   *          the name of the embeddable
   * @param attributeName
   *          the name of the column
   * @param value
   *          the searched value
   * @return the predicate
   */
  protected final Predicate getLike(final Root<E> root, final String attributeName, final String value) {
    final CriteriaBuilder builder = getCriteriaBuilder();
    return builder.like(builder.lower(root.<String> get(attributeName)),
        '%' + value.toLowerCase(Locale.getDefault()) + '%');
  }

  /**
   * Set the first and max results.
   * 
   * @param query
   *          the query
   * @param entityList
   *          the entity list
   */
  protected final void setLimites(final TypedQuery<E> query, final Paged<E> entityList) {
    final int maxResults = entityList.getNbRows();
    int numPage = entityList.getPageNum();
    final int totalPages = entityList.getTotalPages();
    if (numPage == 0 || totalPages == 0) {
      numPage = 1;
      entityList.setPageNum(numPage);
    } else if (numPage > totalPages) {
      numPage = totalPages;
      entityList.setPageNum(numPage);
    }
    final int firstResult = (numPage - 1) * maxResults;
    query.setFirstResult(firstResult).setMaxResults(maxResults);
  }

  /**
   * Set the predicate for the findByExample request.
   *
   * @param root
   *          the root type
   * @param example
   *          the example
   * @return an array of predicates
   */
  protected abstract Predicate[] getPredicates(Root<E> root, E example);

  /**
   * Returns the criteria default order.
   * 
   * @param builder
   *          the criteria builder
   * @param root
   *          the root type
   * @return the criteria order
   */
  protected abstract List<Order> getOrder(CriteriaBuilder builder, Root<E> root);

}
