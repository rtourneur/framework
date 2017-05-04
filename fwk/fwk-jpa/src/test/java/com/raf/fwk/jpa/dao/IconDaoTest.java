package com.raf.fwk.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.raf.fwk.jpa.domain.Icon;

/**
 * Test class for {@link IconDao}.
 * 
 * @author RAF
 */
public class IconDaoTest extends AbstractDaoTest {

  /** Name for the tests. */
  private static final String NAME = "icon.fwk";

  /** Test DAO. */
  @Resource
  private IconDao iconDao;
  
  
  /**
   * Test method for {@link IconDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    Icon result = this.iconDao.getById(NAME);
    assertNotNull(result);
    assertEquals(NAME, result.getName());
    result = this.iconDao.getById("test");
    assertNull(result);
  }

  /**
   * Test method for {@link IconDao#persist(Icon)}.
   */
  @Test
  @Transactional
  @Rollback
  public void testPersist() {
    String name = "test-persist";
    Icon icon = new Icon();
    icon.setName(name);
    icon.setMessageCode("test.code");
    icon.setIcon("test.icon");
    this.iconDao.persist(icon);
    flush();
    Icon result = this.iconDao.getById(name);
    assertNotNull(result);
    assertEquals(name, result.getName());
    assertEquals(icon.getName(), result.getName());
    assertEquals(icon.getMessageCode(), result.getMessageCode());
    assertEquals(icon.getIcon(), result.getIcon());
  }

  /**
   * Test method for {@link IconDao#merge(Icon)}.
   */
  @Test
  @Transactional
  @Rollback
  public void testMerge() {
    String name = "test-merge";
    Icon icon = new Icon();
    icon.setName(name);
    icon.setMessageCode("test.code");
    icon.setIcon("test.icon");
    this.iconDao.persist(icon);
    flush();
    icon.setMessageCode("test-merge.code");
    icon.setIcon("test-merge.icon");
    this.iconDao.merge(icon);
    flush();
    Icon result = this.iconDao.getById(name);
    assertNotNull(result);
    assertEquals(name, result.getName());
    assertEquals(icon.getMessageCode(), result.getMessageCode());
    assertEquals(icon.getIcon(), result.getIcon());
  }

  /**
   * Test method for {@link IconDao#remove(Icon)}.
   */
  @Test
  @Transactional
  @Rollback
  public void testRemove() {
    String name = "test-remove";
    Icon icon = new Icon();
    icon.setName(name);
    icon.setMessageCode("test.code");
    icon.setIcon("test.icon");
    this.iconDao.persist(icon);
    flush();
    Icon result = this.iconDao.getById(name);
    assertNotNull(result);
    assertEquals(name, result.getName());
    this.iconDao.remove(icon);
    flush();
    result = this.iconDao.getById(name);
    assertNull(result);
  }

  /**
   * Test method for {@link IconDao#countByExample(Icon)}.
   */
  @Test
  public void testCountByExample() {
    Icon example = new Icon();
    Long count = this.iconDao.countByExample(example);
    assertEquals(Long.valueOf(18), count);
    example.setName(NAME);
    count = this.iconDao.countByExample(example);
    assertEquals(Long.valueOf(1), count);
  }

  /**
   * Test method for {@link IconDao#findByExample(Icon)}.
   */
  @Test
  public void testFindByExample() {
    Icon example = new Icon();
    List<Icon> result = this.iconDao.findByExample(example);
    assertNotNull(result);
    assertEquals(18, result.size());
    example.setName(NAME);
    result = this.iconDao.findByExample(example);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(NAME, result.get(0).getName());
    example.setName(null);
    example.setIcon("fwk");
    result = this.iconDao.findByExample(example);
    assertNotNull(result);
    assertEquals(4, result.size());
  }

  /**
   * Test method for {@link IconDao#listAll()}.
   */
  @Test
  public void testListAll() {
    List<Icon> result = this.iconDao.listAll();
    assertNotNull(result);
    assertEquals(18, result.size());
  }

  /**
   * Test method for {@link IconDao#list(int, int)}.
   */
  @Test
  public void testListIntInt() {
    List<Icon> result = this.iconDao.list(10,1);
    assertNotNull(result);
    assertEquals(10, result.size());
    result = this.iconDao.list(10,2);
    assertNotNull(result);
    assertEquals(8, result.size());
  }

  /**
   * Test method for {@link IconDao#list(Icon, int, int)}.
   */
  @Test
  public void testListEIntInt() {
    Icon example = new Icon();
    example.setName(NAME);
    List<Icon> result = this.iconDao.list(example, 10,1);
    assertNotNull(result);
    assertEquals(1, result.size());
  }

}
