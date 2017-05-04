package com.raf.fwk.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.raf.fwk.jpa.domain.Identifier;

/**
 * Test class for {@link IdentifierDao}.
 * 
 * @author RAF
 */
public class IdentifierDaoTest extends AbstractDaoTest {

  /** Name for the tests. */
  private static final Integer IDENT = Integer.valueOf(0);

  /** Test DAO. */
  @Resource
  private IdentifierDao identifierDao;

  /** Description DAO. */
  @Resource
  private DescriptionDao descriptionDao;

  /** Icon DAO. */
  @Resource
  private IconDao iconDao;

  /**
   * Test method for {@link IdentifierDao#getById(Integer)}.
   */
  @Test
  public void testGetById() {
    Identifier result = this.identifierDao.getById(IDENT);
    assertNotNull(result);
    assertEquals(IDENT, result.getIdentifier());
    result = this.identifierDao.getById(Integer.valueOf(-1));
    assertNull(result);
  }

  /**
   * Test method for {@link IdentifierDao#getByExample(Identifier)}.
   */
  @Test
  public void testGetByExample() {
    Identifier example = new Identifier();
    example.setDescription(this.descriptionDao.getById("desc.fwk"));
    example.setIcon(this.iconDao.getById("icon.fwk"));
    Identifier result = this.identifierDao.getByExample(example);
    assertNotNull(result);
    assertEquals(IDENT, result.getIdentifier());
    example = new Identifier();
    example.setDescriptionName("desc.fwk");
    example.setIconName("icon.fwk");
    result = this.identifierDao.getByExample(example);
    assertNotNull(result);
    assertEquals(IDENT, result.getIdentifier());
  }

  /**
   * Test method for {@link IdentifierDao#persist(Identifier)}.
   */
  @Test
  @Transactional
  @Rollback
  public void testPersist() {
    Identifier identifier = new Identifier();
    identifier.setMessageCode("test.code");
    identifier.setDescription(this.descriptionDao.getById("desc.fwk"));
    identifier.setIcon(this.iconDao.getById("icon.descent"));
    this.identifierDao.persist(identifier);
    Integer ident = identifier.getIdent();
    flush();
    Identifier result = this.identifierDao.getById(ident);
    assertNotNull(result);
    assertEquals(ident, result.getIdentifier());
    assertEquals(identifier.getIdentifier(), result.getIdentifier());
    assertEquals(identifier.getMessageCode(), result.getMessageCode());
    assertNotNull(result.getDescription());
    assertEquals(identifier.getDescription().getName(), result.getDescription().getName());
    assertNotNull(result.getIcon());
    assertEquals(identifier.getIcon().getName(), result.getIcon().getName());
  }

  /**
   * Test method for {@link IdentifierDao#merge(Identifier)}.
   */
  @Test
  @Transactional
  @Rollback
  public void testMerge() {
    Identifier identifier = new Identifier();
    identifier.setMessageCode("test.code");
    identifier.setDescription(this.descriptionDao.getById("desc.fwk"));
    identifier.setIcon(this.iconDao.getById("icon.descent"));
    this.identifierDao.persist(identifier);
    Integer ident = identifier.getIdent();
    flush();
    identifier.setMessageCode("test-merge.code");
    identifier.setDescription(this.descriptionDao.getById("desc.descent"));
    identifier.setIcon(this.iconDao.getById("icon.fwk"));
    Identifier merged = this.identifierDao.merge(identifier);
    assertNotNull(merged);
    flush();
    Identifier result = this.identifierDao.getById(ident);
    assertNotNull(result);
    assertEquals(ident, result.getIdentifier());
    assertNotNull(result.getDescription());
    assertEquals(identifier.getDescription().getName(), result.getDescription().getName());
    assertNotNull(result.getIcon());
    assertEquals(identifier.getIcon().getName(), result.getIcon().getName());
  }

  /**
   * Test method for {@link IdentifierDao#remove(Identifier)}.
   */
  @Test
  @Transactional
  @Rollback
  public void testRemove() {
    Identifier identifier = new Identifier();
    identifier.setMessageCode("test.code");
    identifier.setDescription(this.descriptionDao.getById("desc.fwk"));
    identifier.setIcon(this.iconDao.getById("icon.descent"));
    this.identifierDao.persist(identifier);
    Integer ident = identifier.getIdent();
    flush();
    Identifier result = this.identifierDao.getById(ident);
    assertNotNull(result);
    assertEquals(ident, result.getIdentifier());
    this.identifierDao.remove(identifier);
    flush();
    result = this.identifierDao.getById(ident);
    assertNull(result);
  }

  /**
   * Test method for {@link IdentifierDao#countByExample(Identifier)}.
   */
  @Test
  public void testCountByExample() {
    Identifier example = new Identifier();
    Long count = this.identifierDao.countByExample(example);
    assertEquals(Long.valueOf(18), count);
  }

  /**
   * Test method for {@link IdentifierDao#findByExample(Identifier)}.
   */
  @Test
  public void testFindByExample() {
    Identifier example = new Identifier();
    List<Identifier> result = this.identifierDao.findByExample(example);
    assertNotNull(result);
    assertEquals(18, result.size());
    example.setIdentifier(null);
    example.setDescription(this.descriptionDao.getById("desc.fwk"));
    result = this.identifierDao.findByExample(example);
    assertNotNull(result);
    assertEquals(1, result.size());
    example.setDescription(null);
    example.setDescriptionName(null);
    example.setIcon(this.iconDao.getById("icon.fwk"));
    result = this.identifierDao.findByExample(example);
    assertNotNull(result);
    assertEquals(1, result.size());
    example.setIcon(null);
    example.setDescriptionName("desc.fwk");
    result = this.identifierDao.findByExample(example);
    assertNotNull(result);
    assertEquals(4, result.size());
  }

  /**
   * Test method for {@link IdentifierDao#listAll()}.
   */
  @Test
  public void testListAll() {
    List<Identifier> result = this.identifierDao.listAll();
    assertNotNull(result);
    assertEquals(18, result.size());
  }

  /**
   * Test method for {@link IdentifierDao#list(int, int)}.
   */
  @Test
  public void testListIntInt() {
    List<Identifier> result = this.identifierDao.list(10, 1);
    assertNotNull(result);
    assertEquals(10, result.size());
    result = this.identifierDao.list(10, 2);
    assertNotNull(result);
    assertEquals(8, result.size());
  }

  /**
   * Test method for {@link IdentifierDao#list(Identifier, int, int)}.
   */
  @Test
  public void testListEIntInt() {
    Identifier example = new Identifier();
    example.setDescriptionName("desc.fwk");
    List<Identifier> result = this.identifierDao.list(example, 10, 1);
    assertNotNull(result);
    assertEquals(4, result.size());
  }

}
