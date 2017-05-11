package com.raf.fwk.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility method for calculs.
 * 
 * @author RAF
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CalculUtils {

  /**
   * Calculate the average value between two integer.
   * 
   * @param val1
   *          the first integer
   * @param val2
   *          the second integer
   * @return the value, or 0 if any integer is null
   */
  public static int average(final Integer val1, final Integer val2) {
    final int average;
    if (val1 == null || val2 == null) {
      average = 0;
    } else {
      average = (val1.intValue() + val2.intValue()) / 2;
    }
    return average;
  }

  /**
   * Calculate the average value between an int and an integer.
   * 
   * @param val1
   *          the int value
   * @param val2
   *          the integer value
   * @return the value or 0 if any integer is null
   */
  public static int average(final int val1, final Integer val2) {
    final int average;
    if (val2 == null) {
      average = 0;
    } else {
      average = (val1 + val2.intValue()) / 2;
    }
    return average;
  }

  /**
   * Calculate the average value between two integer, rounding up.
   * 
   * @param val1
   *          the first integer
   * @param val2
   *          the second integer
   * @return the value or 0 if any integer is null
   */
  public static int averageUp(final Integer val1, final Integer val2) {
    final int average;
    if (val1 == null || val2 == null) {
      average = 0;
    } else {
      average = (val1.intValue() + val2.intValue() + 1) / 2;
    }
    return average;
  }

  /**
   * Calculate the sum value from two integer.
   * 
   * @param val1
   *          the first integer
   * @param val2
   *          the second integer
   * @return the value or 0 if any integer is null
   */
  public static int sum(final Integer val1, final Integer val2) {
    final int sum;
    if (val1 == null || val2 == null) {
      sum = 0;
    } else {
      sum = val1.intValue() + val2.intValue();
    }
    return sum;
  }

  /**
   * Calculate the max value from two integer.
   * 
   * @param val1
   *          the first integer
   * @param val2
   *          the second integer
   * @return the value or 0 if any integer is null
   */
  public static int max(final Integer val1, final Integer val2) {
    final int max;
    if (val1 == null || val2 == null) {
      max = 0;
    } else {
      max = Math.max(val1.intValue(), val2.intValue());
    }
    return max;
  }
}
