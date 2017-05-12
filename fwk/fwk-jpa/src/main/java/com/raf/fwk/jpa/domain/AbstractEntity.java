package com.raf.fwk.jpa.domain;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Abstract super class for domain entities.
 *
 * @author RAF
 */
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractEntity {

  /** Version for the object. */
  @Column(name = "VERSION", nullable = false, columnDefinition = "INT default 0")
  @Version
  private Integer version;

  /**
   * Generate the toString.
   *
   * @see Object#toString()
   */
  @Override
  public final String toString() {
    final ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
    append(builder);
    builder.append("version", this.version);
    return builder.toString();
  }

  /**
   * Append the properties for the to string builder.
   * 
   * @param builder
   *          the builder
   */
  protected abstract void append(ToStringBuilder builder);

  /**
   * Create a new Date object from the date.
   * 
   * @param date
   *          the date
   * 
   * @return a new date
   */
  protected final Date getFromDate(final Date date) {
    final Date result;
    if (date == null) {
      result = date;
    } else {
      result = new Date(date.getTime());
    }
    return result;
  }

}
