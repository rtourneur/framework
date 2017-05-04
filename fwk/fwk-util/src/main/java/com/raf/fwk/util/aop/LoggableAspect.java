package com.raf.fwk.util.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Log class.
 * 
 * @author RAF
 */
@Slf4j
@Component
@Aspect
public final class LoggableAspect {

  /**
   * Cosntructor.
   */
  public LoggableAspect() {
    super();
  }

  /**
   * Log the call to the method whith {@link com.raf.fwk.util.aop.descent.util.Loggable} annotation. Log the method name
   * and the parameters.
   * 
   * @param joinpoint
   *          the joint point
   */
  @Before("@annotation(com.raf.fwk.util.aop.Loggable)")
  public void logCall(final JoinPoint joinpoint) {

    if (log.isInfoEnabled()) {
      final Object[] args = joinpoint.getArgs();
      final StringBuilder builder = new StringBuilder();
      builder.append(joinpoint.getTarget().getClass()).append('.').append(joinpoint.getSignature().getName())
          .append(" avec les parametres : (");
      Object object;
      for (int i = 0; i < args.length; i++) {
        object = args[i];
        if (object != null) {
          builder.append(object.toString());
        }
        if (i < args.length - 1) {
          builder.append(", ");
        }
      }
      builder.append(')');
      log.info("Début méthode : {}", builder.toString());
    }
  }

  /**
   * Log the duration of the method whith {@link com.raf.fwk.util.aop.descent.util.Loggable} annotation.
   * 
   * @param joinpoint
   *          the joint point
   * @throws Throwable
   *           the throwable from the method
   */
  @Around("@annotation(com.raf.fwk.util.aop.Loggable)")
  public Object logDuration(final ProceedingJoinPoint joinpoint) throws Throwable {
    final Object result;
    if (log.isInfoEnabled()) {
      final long start = System.currentTimeMillis();
      result = joinpoint.proceed();
      final long end = System.currentTimeMillis();
      final StringBuilder builder = new StringBuilder();
      builder.append(joinpoint.getTarget().getClass()).append('.').append(joinpoint.getSignature().getName())
          .append(':').append(end - start);
      log.info("Durée methode : {}", builder.toString());
    } else {
      result = joinpoint.proceed();
    }
    return result;
  }
}
