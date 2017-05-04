package com.raf.fwk.jpa.dao;

import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.raf.fwk.jpa.domain.Description;

/**
 * Implementation DAO for {@link Description}.
 * 
 * @author RAF
 */
@Repository
public class DescriptionDaoImpl extends AbstractNamedDao<Description> implements DescriptionDao {

  /**
   * Constructor.
   */
  public DescriptionDaoImpl() {
    super(Description.class);
  }

  /**
   * Append the predicates for the findByExample request.
   * <ul>
   * <li>description</li>
   * </ul>
   * 
   * @param predicatesList
   *          the list of predicates
   * @param root
   *          the root type
   * @param example
   *          the example
   * @see AbstractNamedDao#addPredicates(List, Root, Description)
   */
  @Override
  protected void addPredicates(List<Predicate> predicatesList, Root<Description> root, Description example) {
    if (example.getDescription() != null) {
      predicatesList.add(getLike(root, "description", example.getDescription()));
    }
  }

}
