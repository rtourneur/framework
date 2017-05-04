package com.raf.fwk.util;

import java.util.List;

/**
 * Interface for pagination.
 * 
 * @author RAF
 * @param <E>
 *          the paged object
 *
 */
public interface Paged<E> extends List<E> {

  /**
   * Return the number of rows in the result list.
   * 
   * @return the number of rows
   */
  int getNbRows();

  /**
   * Define the number of rows in the result list.
   * 
   * @param nbRows
   *          the number of rows
   */
  void setNbRows(int nbRows);

  /**
   * Return the chosen page number.
   * 
   * @return the page number
   */
  int getPageNum();

  /**
   * Define the chosen page number.
   * 
   * @param pageNum
   *          the page number
   */
  void setPageNum(int pageNum);

  /**
   * Return the total number of rows.
   * 
   * @return the total number of rows
   */
  int getTotalRows();

  /**
   * Define the total number of rows.
   * 
   * @param totalRows
   *          the total number of rows
   */
  void setTotalRows(int totalRows);

  /**
   * Return the total number of pages.
   * 
   * @return the total number of pages
   */
  int getTotalPages();
}
