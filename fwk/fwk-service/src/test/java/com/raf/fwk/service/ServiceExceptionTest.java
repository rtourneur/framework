package com.raf.fwk.service;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

/**
 * @author tourneurr
 *
 */
public class ServiceExceptionTest {

  /**
   * Test method for {@link ServiceException#getMessage(Locale)}.
   */
  @Test
  public void testGetMessageLocale() {
    ServiceException serviceException = new ServiceException("error.test", "junit");
    assertEquals("Error message junit.", serviceException.getMessage(Locale.ENGLISH));
    assertEquals("Message d'erreur junit.", serviceException.getMessage(Locale.FRENCH));
  }

}
