package com.raf.fwk.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.raf.fwk.jpa.config.PersistenceJpaConfig;

/**
 * 
 * Abstract class for all DAO tests.
 * 
 * @author RAF
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { PersistenceJpaConfig.class }, loader = AnnotationConfigContextLoader.class)
public abstract class AbstractDaoTest {

  /** The persistence context. */
  @PersistenceContext
  private transient EntityManager entityManager;

  /**
   * Constructor.
   */
  public AbstractDaoTest() {
    super();
  }

  /**
   * Flush the persistence context.
   */
  protected final void flush() {
    this.entityManager.flush();
  }
}
