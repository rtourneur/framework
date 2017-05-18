package com.raf.fwk.service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import lombok.Getter;

/**
 * Service exception.
 * 
 * @author RAF
 */
public class ServiceException extends Exception {

  /** Serial UID. */
  private static final long serialVersionUID = 8258685951000662441L;

  /** Map for localized messages bundle. */
  private static final Map<Locale, ResourceBundle> MESSAGES_BUNDLE = new HashMap<>();

  /** Clef du message dans la log. */
  @Getter
  private final String messageKey;

  /** Paramètres du message. */
  private final transient Object[] parameters;

  /**
   * Default constructor.
   */
  public ServiceException() {
    super();
    this.messageKey = null;
    this.parameters = null;
  }

  /**
   * Constructor with message key.
   * 
   * @param messageKey
   *          key for the message
   * @param args
   *          arguments for the message
   */
  public ServiceException(final String messageKey, final Object... args) {
    super(getMessage(messageKey, args));
    this.messageKey = messageKey;
    this.parameters = args;
  }

  /**
   * Default constructor from exception.
   * 
   * @param cause
   *          the exception cause
   */
  public ServiceException(final Throwable cause) {
    super(cause);
    this.messageKey = null;
    this.parameters = null;
  }

  /**
   * Constructor with exception and message key.
   * 
   * @param cause
   *          the exception cause
   * @param messageKey
   *          key for the message
   * @param args
   *          arguments for the message
   */
  public ServiceException(final Throwable cause, final String messageKey, final Object... args) {
    super(getMessage(messageKey, args), cause);
    this.messageKey = messageKey;
    this.parameters = args;
  }

  /**
   * Get the message from the message key.
   * 
   * @param locale
   *          the locale.
   * 
   * @return the localized message
   */
  public final String getMessage(final Locale locale) {
    return getMessage(locale, this.messageKey, this.parameters);
  }

  /**
   * Get the message from the message key and the default locale.
   * 
   * @param messageKey
   *          key for the message
   * @param args
   *          arguments for the message
   * @return the localized message
   */
  private static String getMessage(final String messageKey, final Object... args) {
    return getMessage(Locale.getDefault(), messageKey, args);
  }

  /**
   * Get the message from the message key and the locale.
   * 
   * @param locale
   *          the locale
   * @param messageKey
   *          key for the message
   * @param args
   *          arguments for the message
   * @return the localized message
   */
  private static String getMessage(final Locale locale, final String messageKey, final Object... args) {
    final ResourceBundle bundle = getMessageBundle(locale);
    return MessageFormat.format(bundle.getString(messageKey), args);
  }

  /**
   * Retrieve the message bundle for the locale.
   * 
   * @param locale
   *          the locale
   * @return the message bundle
   */
  private static ResourceBundle getMessageBundle(final Locale locale) {
    if (!MESSAGES_BUNDLE.containsKey(locale)) {
      final ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
      MESSAGES_BUNDLE.put(locale, bundle);
    }
    return MESSAGES_BUNDLE.get(locale);
  }
}
