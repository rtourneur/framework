package com.raf.fwk.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tourneurr
 *
 */
public class CalculUtilsTest {

  /**
   * Test method for {@link CalculUtils#average(Integer, Integer)}, {@link CalculUtils#average(int, Integer)} and
   * {@link CalculUtils#averageUp(Integer, Integer)}.
   */
  @Test
  public void testAverage() {
    Integer val1 = Integer.valueOf(10);
    Integer val2 = Integer.valueOf(12);

    Assert.assertEquals(11, CalculUtils.average(val1, val2));

    Integer val3 = Integer.valueOf(11);
    Assert.assertEquals(11, CalculUtils.average(val3, val2));

    Assert.assertEquals(0, CalculUtils.average(val3, null));
    Assert.assertEquals(0, CalculUtils.average(null, val3));

    Assert.assertEquals(11, CalculUtils.average(10, val2));

    Assert.assertEquals(0, CalculUtils.average(11, null));

    Assert.assertEquals(11, CalculUtils.averageUp(val1, val2));
    Assert.assertEquals(12, CalculUtils.averageUp(val3, val2));

    Assert.assertEquals(0, CalculUtils.averageUp(val3, null));
    Assert.assertEquals(0, CalculUtils.averageUp(null, val3));
  }

  /**
   * Test method for {@link CalculUtils#sum(Integer, Integer)}.
   */
  @Test
  public void testSum() {
    Integer val1 = Integer.valueOf(10);
    Integer val2 = Integer.valueOf(12);
    Assert.assertEquals(22, CalculUtils.sum(val1, val2));
    Assert.assertEquals(0, CalculUtils.sum(val1, null));
    Assert.assertEquals(0, CalculUtils.sum(null, val2));
  }

  /**
   * Test method for {@link CalculUtils#max(Integer, Integer)}.
   */
  @Test
  public void testMax() {
    Integer val1 = Integer.valueOf(10);
    Integer val2 = Integer.valueOf(12);
    Assert.assertEquals(12, CalculUtils.max(val1, val2));
    Assert.assertEquals(0, CalculUtils.max(val1, null));
    Assert.assertEquals(0, CalculUtils.max(null, val2));
  }

}
