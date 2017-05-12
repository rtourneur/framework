package com.raf.fwk.security.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.raf.fwk.jpa.dao.AbstractDao;
import com.raf.fwk.jpa.dao.AbstractIdDao;
import com.raf.fwk.security.dao.UserEntityDao;
import com.raf.fwk.security.domain.UserEntity;

/**
 * Implementation DAO for {@link UserEntityDao}.
 * 
 * @author RAF
 *
 */
@Repository
public final class UserEntityDaoImpl extends AbstractIdDao<UserEntity> implements UserEntityDao {

  /**
   * Constructor.
   */
  public UserEntityDaoImpl() {
    super(UserEntity.class);
  }

  /**
   * Find the user whith the username.
   * 
   * @param username
   *          the username
   * @return the user if found, <code>null</code> otherwise
   * @see UserEntityDao#findByUsername(String)
   */
  @Override
  public UserEntity findByUsername(final String username) {
    final CriteriaQuery<UserEntity> query = getQuery();
    final Root<UserEntity> root = getRoot(query);
    final Predicate[] predicates = new Predicate[1];
    predicates[0] = getEquals(root, "username", username);
    final TypedQuery<UserEntity> typedQuery = getTypedQuery(query.select(root).where(predicates));
    return typedQuery.getSingleResult();
  }

  /**
   * Set the predicate for the getByExample request.
   * <ul>
   * <li>username</li>
   * </ul>
   *
   * @param root
   *          the root type
   * @param example
   *          the example
   * @return an array of predicates
   * @see AbstractIdDao#getUniquePredicates(Root, com.raf.fwk.jpa.domain.DomainIdEntity)
   */
  @Override
  protected Predicate[] getUniquePredicates(final Root<UserEntity> root, final UserEntity example) {
    final Predicate[] predicates = new Predicate[1];
    predicates[0] = getEquals(root, "username", example.getUsername());
    return predicates;
  }

  /**
   * Set the predicates for the findByExample request.
   * <ul>
   * <li>username</li>
   * <li>enabled</li>
   * <li>mail</li>
   * </ul>
   *
   * @param root
   *          the root type
   * @param example
   *          the example
   * @see AbstractDao#getPredicates(Root, com.raf.fwk.jpa.domain.DomainEntity)
   */
  @Override
  protected Predicate[] getPredicates(final Root<UserEntity> root, final UserEntity example) {
    final List<Predicate> predicatesList = new ArrayList<>();
    if (example.getUsername() != null) {
      predicatesList.add(getLike(root, "username", example.getUsername()));
    }
    if (example.isEnabled()) {
      predicatesList.add(getEquals(root, "enabled", Boolean.valueOf(example.isEnabled())));
    }
    if (example.getMail() != null) {
      predicatesList.add(getLike(root, "mail", example.getMail()));
    }
    return predicatesList.toArray(new Predicate[predicatesList.size()]);
  }

  /**
   * Append the criteria default order.
   * <ul>
   * <li>username</li>
   * </ul>
   * 
   * @param orders
   *          the orders list
   * @param builder
   *          the criteria builder
   * @param root
   *          the root type
   * 
   * @see AbstractIdDao#appendOrder(List, CriteriaBuilder, Root)
   */
  @Override
  protected void appendOrder(final List<Order> orders, final CriteriaBuilder builder, final Root<UserEntity> root) {
    orders.add(builder.asc(root.get("username")));
  }

}
