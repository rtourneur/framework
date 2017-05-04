package com.raf.fwk.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.raf.fwk.jpa.domain.DomainNamedEntity;

/**
 * Abstract implementation for all DAOs with String Id.
 *
 * @author RAF
 * @param <E>
 *          the interface entity
 */
public abstract class AbstractNamedDao<E extends DomainNamedEntity<String>> extends
    AbstractDao<E, String> {

  /**
   * Constructor.
   *
   * @param entityClass
   *          the entity class.
   */
  protected AbstractNamedDao(final Class<? extends E> entityClass) {
    super(entityClass);
  }

  /**
   * Set the predicates for the findByExample request.
   * <ul>
   * <li>name</li>
   * </ul>
   *
   * @param root
   *          the root type
   * @param example
   *          the example
   * @see AbstractDao#getPredicates(Root, DomainNamedEntity)
   */
  @Override
  protected final Predicate[] getPredicates(final Root<E> root, final E example) {
    final List<Predicate> predicatesList = new ArrayList<>();
    if (example.getName() != null) {
      predicatesList.add(getEquals(root, NAME, example.getName()));
    }
    addPredicates(predicatesList, root, example);
    return predicatesList.toArray(new Predicate[predicatesList.size()]);
  }

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
  protected final List<Order> getOrder(final CriteriaBuilder builder, final Root<E> root) {
    final List<Order> orders = new ArrayList<>();
    orders.add(builder.asc(root.get(NAME)));
    return orders;
  }

  /**
   * Append the predicates for the findByExample request.
   * 
   * @param predicatesList
   *          the list of predicates
   * @param root
   *          the root type
   * @param example
   *          the example
   */
  protected void addPredicates(final List<Predicate> predicatesList, final Root<E> root, final E example) {
    // Empty method for overriding.
  }

}
