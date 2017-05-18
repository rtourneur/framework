package com.raf.fwk.webtest.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import com.raf.fwk.security.config.SecurityConfig;
import com.raf.fwk.util.config.UtilConfig;

import lombok.NoArgsConstructor;

/**
 * Class initializer.
 * 
 * @author RAF
 */
@NoArgsConstructor
public final class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * Return the array of configurations classes for spring root configuration.
   * 
   * @return the array of spring configuration classes
   * @see AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { UtilConfig.class, WebConfig.class, SecurityConfig.class };
  }

  /**
   * Return the array of configurations classes for spring servlet configuration.
   * 
   * @return the array of spring servlet configuration classes
   * @see AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { DispatcherConfig.class };
  }

  /**
   * Return the array of servlet mappings.
   * 
   * @return the array of servlet mappings
   * @see AbstractDispatcherServletInitializer#getServletMappings()
   */
  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}
