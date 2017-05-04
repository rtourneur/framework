package com.raf.fwk.util.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * Spring configuration class for utils classes.
 * 
 * @author RAF
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.raf.fwk.util.aop")
public class UtilConfig {

  /**
   * Constructor.
   */
  public UtilConfig() {
    super();
  }

}
