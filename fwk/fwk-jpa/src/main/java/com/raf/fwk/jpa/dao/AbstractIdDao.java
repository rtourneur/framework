package com.raf.fwk.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.raf.fwk.jpa.domain.DomainIdEntity;
import com.raf.fwk.util.aop.Loggable;

/**
 * Abstract implementation for all DAOs with technical Id and functionnal identifier.
 *
 * @author RAF
 * @param <E>
 *          the interface entity
 */
public abstract class AbstractIdDao<E extends DomainIdEntity> extends AbstractDao<E, Integer>
    implements EntityIdDao<E> {

  /**
   * Constructor.
   * 
   * @param entityClass
   *          the entity class
   */
  protected AbstractIdDao(final Class<? extends E> entityClass) {
    super(entityClass);
  }

  /**
   * Retrieve the entity with it's functionnal id.
   *
   * @param example
   *          the example instance with functionnal id
   * @return the entity
   * @see EntityIdDao#getByExample(DomainIdEntity)
   */
  @Override
  @Loggable
  public final E getByExample(final E example) {
    final CriteriaQuery<E> query = getQuery();
    final Root<E> root = getRoot(query);
    final Predicate[] predicates = getUniquePredicates(root, example);
    final TypedQuery<E> typedQuery = getTypedQuery(query.select(root).where(predicates));
    return typedQuery.getSingleResult();
  }

  /**
   * Set the predicate for the getByExample request.
   *
   * @param root
   *          the root type
   * @param example
   *          the example
   * @return an array of predicates
   */
  protected abstract Predicate[] getUniquePredicates(Root<E> root, E example);

  /**
   * Returns the criteria default order.
   * 
   * @param builder
   *          the criteria builder
   * @param root
   *          the root type
   * @return the criteria order
   * @see AbstractDao#getOrder(CriteriaBuilder, Root)
   */
  @Override
  protected List<Order> getOrder(final CriteriaBuilder builder, final Root<E> root) {
    final List<Order> orders = new ArrayList<>();
    appendOrder(orders, builder, root);
    orders.add(builder.asc(root.get(IDENT)));
    return orders;
  }

  /**
   * Append the criteria default order.
   * 
   * @param orders
   *          the orders list
   * @param builder
   *          the criteria builder
   * @param root
   *          the root type
   */
  protected void appendOrder(final List<Order> orders, final CriteriaBuilder builder, final Root<E> root) {
    // Empty method for overriding.
  }

}
