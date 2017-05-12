package com.raf.fwk.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementation for the paged interface.
 * 
 * @author RAF
 * @param <E>
 *          the paged object
 */
public final class PagedImpl<E> extends ArrayList<E> implements Paged<E> {

  /** Serial UID. */
  private static final long serialVersionUID = 1L;

  /** The number of rows. */
  private int nbRows;

  /** The page number. */
  private int pageNum;

  /** The total number of rows. */
  private int totalRows;

  /**
   * Constructor.
   */
  public PagedImpl() {
    super();
  }

  /**
   * Constructor.
   * 
   * @param initialCapacity
   *          Constructor.
   */
  public PagedImpl(final int initialCapacity) {
    super(initialCapacity);
  }

  /**
   * Constructor.
   * 
   * @param coll
   *          the collection whose elements are to be placed into this list
   */
  public PagedImpl(final Collection<? extends E> coll) {
    super(coll);
  }

  /**
   * Return the number of rows in the result list.
   * 
   * @return the number of rows
   * @see Paged#getNbRows()
   */
  @Override
  public int getNbRows() {
    return this.nbRows;
  }

  /**
   * Define the number of rows in the result list.
   * 
   * @param nbRows
   *          the number of rows
   * @see Paged#setNbRows(int)
   */
  @Override
  public void setNbRows(final int nbRows) {
    this.nbRows = nbRows;
  }

  /**
   * Return the chosen page number.
   * 
   * @return the page number
   * @see Paged#getPageNum()
   */
  @Override
  public int getPageNum() {
    return this.pageNum;
  }

  /**
   * Define the chosen page number.
   * 
   * @param pageNum
   *          the page number
   * @see Paged#setPageNum(int)
   */
  @Override
  public void setPageNum(final int pageNum) {
    this.pageNum = pageNum;
  }

  /**
   * Return the total number of rows.
   * 
   * @return the total number of rows
   * @see Paged#getTotalRows()
   */
  @Override
  public int getTotalRows() {
    return this.totalRows;
  }

  /**
   * Define the total number of rows.
   * 
   * @param totalRows
   *          the total number of rows
   * @see Paged#setTotalRows(int)
   */
  @Override
  public void setTotalRows(final int totalRows) {
    this.totalRows = totalRows;
  }

  /**
   * Return the total number of pages.
   * 
   * @return the total number of pages
   * @see Paged#getTotalPages()
   */
  @Override
  public int getTotalPages() {
    return (int) Math.ceil((double) this.totalRows / this.nbRows);
  }

}
