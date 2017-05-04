package com.raf.fwk.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.raf.fwk.jpa.domain.Identifier;

/**
 * Implementation DAO for {@link Identifier}.
 * 
 * @author RAF
 */
@Repository
public class IdentifierDaoImpl extends AbstractIdDao<Identifier> implements IdentifierDao {

  /**
   * Constructor.
   */
  public IdentifierDaoImpl() {
    super(Identifier.class);
  }

  /**
   * Set the predicate for the getByExample request.
   *
   * @param root
   *          the root type
   * @param example
   *          the example
   * @return an array of predicates
   * @see AbstractIdDao#getUniquePredicates(Root, Identifier)
   */
  @Override
  protected Predicate[] getUniquePredicates(Root<Identifier> root, Identifier example) {
    final List<Predicate> predicatesList = new ArrayList<>();
    if (example.getDescription() == null) {
      predicatesList.add(getEquals(root, "descriptionName", example.getDescriptionName()));
    } else {
      predicatesList.add(getEquals(root, "description", example.getDescription()));
    }
    if (example.getIcon() == null) {
      predicatesList.add(getEquals(root, "iconName", example.getIconName()));
    } else {
      predicatesList.add(getEquals(root, "icon", example.getIcon()));
    }
    return predicatesList.toArray(new Predicate[predicatesList.size()]);
  }

  /**
   * Set the predicate for the findByExample request.
   *
   * @param root
   *          the root type
   * @param example
   *          the example
   * @return an array of predicates
   * @see AbstractDao#getPredicates(Root, Identifier)
   */
  @Override
  protected Predicate[] getPredicates(Root<Identifier> root, Identifier example) {
    final List<Predicate> predicatesList = new ArrayList<>();
    if (example.getDescription() == null) {
      if (example.getDescriptionName() != null) {
        predicatesList.add(getLike(root, "descriptionName", example.getDescriptionName()));
      }
    } else {
      predicatesList.add(getEquals(root, "description", example.getDescription()));
    }
    if (example.getIcon() == null) {
      if (example.getIconName() != null) {
        predicatesList.add(getLike(root, "iconName", example.getIconName()));
      }
    } else {
      predicatesList.add(getEquals(root, "icon", example.getIcon()));
    }
    return predicatesList.toArray(new Predicate[predicatesList.size()]);
  }

}
