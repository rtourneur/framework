package com.raf.fwk.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.raf.fwk.jpa.domain.Icon;

/**
 * Implementation DAO for {@link Icon}.
 * 
 * @author RAF
 */
@Repository
public class IconDaoImpl extends AbstractDao<Icon, String> implements IconDao {

  /**
   * Constructor.
   */
  public IconDaoImpl() {
    super(Icon.class);
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
   * @see AbstractDao#getPredicates(Root, Icon)
   */
  @Override
  protected Predicate[] getPredicates(Root<Icon> root, Icon example) {
    final List<Predicate> predicatesList = new ArrayList<>();
    if (example.getName() != null) {
      predicatesList.add(getEquals(root, NAME, example.getName()));
    }
    if (example.getIcon() != null) {
      predicatesList.add(getLike(root, "icon", example.getIcon()));
    }
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
  protected List<Order> getOrder(CriteriaBuilder builder, Root<Icon> root) {
    final List<Order> orders = new ArrayList<>();
    orders.add(builder.asc(root.get(NAME)));
    return orders;
  }

}
