package com.raf.fwk.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.raf.fwk.jpa.domain.Description;

/**
 * Test class for {@link DescriptionDao}.
 * 
 * @author RAF
 */
public class DescriptionDaoTest extends AbstractDaoTest {

  /** Name for the tests. */
  private static final String NAME = "desc.fwk";
  
  /** Test DAO. */
  @Resource
  private DescriptionDao descriptionDao;
  
  
  /**
   * Test method for {@link DescriptionDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    Description result = this.descriptionDao.getById(NAME);
    assertNotNull(result);
    assertEquals(NAME, result.getName());
    assertEquals(NAME, result.getIdentifier());
    result = this.descriptionDao.getById("test");
    assertNull(result);
  }

  /**
   * Test method for {@link DescriptionDao#persist(Description)}.
   */
  @Test
  @Transactional
  @Rollback
  public void testPersist() {
    String name = "test-persist";
    Description description = new Description();
    description.setName(name);
    description.setMessageCode("test.code");
    description.setDescription("test.description");
    this.descriptionDao.persist(description);
    flush();
    Description result = this.descriptionDao.getById(name);
    assertNotNull(result);
    assertEquals(name, result.getName());
    assertEquals(description.getName(), result.getName());
    assertEquals(description.getMessageCode(), result.getMessageCode());
    assertEquals(description.getDescription(), result.getDescription());
  }

  /**
   * Test method for {@link DescriptionDao#merge(Description)}.
   */
  @Test
  @Transactional
  @Rollback
  public void testMerge() {
    String name = "test-merge";
    Description description = new Description();
    description.setIdentifier(name);
    description.setMessageCode("test.code");
    description.setDescription("test.description");
    this.descriptionDao.persist(description);
    flush();
    description.setMessageCode("test-merge.code");
    description.setDescription("test-merge.description");
    this.descriptionDao.merge(description);
    flush();
    Description result = this.descriptionDao.getById(name);
    assertNotNull(result);
    assertEquals(name, result.getName());
    assertEquals(description.getMessageCode(), result.getMessageCode());
    assertEquals(description.getDescription(), result.getDescription());
  }

  /**
   * Test method for {@link DescriptionDao#remove(Description)}.
   */
  @Test
  @Transactional
  @Rollback
  public void testRemove() {
    String name = "test-remove";
    Description description = new Description();
    description.setName(name);
    description.setMessageCode("test.code");
    description.setDescription("test.description");
    this.descriptionDao.persist(description);
    flush();
    Description result = this.descriptionDao.getById(name);
    assertNotNull(result);
    assertEquals(name, result.getName());
    this.descriptionDao.remove(description);
    flush();
    result = this.descriptionDao.getById(name);
    assertNull(result);
  }

  /**
   * Test method for {@link DescriptionDao#countByExample(Description)}.
   */
  @Test
  public void testCountByExample() {
    Description example = new Description();
    Long count = this.descriptionDao.countByExample(example);
    assertEquals(Long.valueOf(18), count);
    example.setName(NAME);
    count = this.descriptionDao.countByExample(example);
    assertEquals(Long.valueOf(1), count);
  }

  /**
   * Test method for {@link DescriptionDao#findByExample(Description)}.
   */
  @Test
  public void testFindByExample() {
    Description example = new Description();
    List<Description> result = this.descriptionDao.findByExample(example);
    assertNotNull(result);
    assertEquals(18, result.size());
    example.setName(NAME);
    result = this.descriptionDao.findByExample(example);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(NAME, result.get(0).getName());
    example.setName(null);
    example.setDescription("Persistence");
    result = this.descriptionDao.findByExample(example);
    assertNotNull(result);
    assertEquals(5, result.size());
  }

  /**
   * Test method for {@link DescriptionDao#listAll()}.
   */
  @Test
  public void testListAll() {
    List<Description> result = this.descriptionDao.listAll();
    assertNotNull(result);
    assertEquals(18, result.size());
  }

  /**
   * Test method for {@link DescriptionDao#list(int, int)}.
   */
  @Test
  public void testListIntInt() {
    List<Description> result = this.descriptionDao.list(10,1);
    assertNotNull(result);
    assertEquals(10, result.size());
    result = this.descriptionDao.list(10,2);
    assertNotNull(result);
    assertEquals(8, result.size());
  }

  /**
   * Test method for {@link DescriptionDao#list(Description, int, int)}.
   */
  @Test
  public void testListEIntInt() {
    Description example = new Description();
    example.setName(NAME);
    List<Description> result = this.descriptionDao.list(example, 10,1);
    assertNotNull(result);
    assertEquals(1, result.size());
  }

}
