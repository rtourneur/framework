package com.raf.fwk.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the IDENTIFIER database table.
 * 
 * @author RAF
 */
@Entity
@Table(name = "IDENTIFIER", schema = "FWK", indexes = @Index(name = "IDX_IDENTIFIER", columnList = "DESCRIPTION, ICON", unique = true))
@Getter
@Setter
@NoArgsConstructor
public class Identifier extends AbstractIdEntity {

  /** Serial UID. */
  private static final long serialVersionUID = 9004699143624652231L;

  /** The message code for internationalisation. */
  @Column(name = "MESSAGE_CODE", nullable = false, length = 40)
  private String messageCode;

  /** The name of the description. */
  @Column(name = "DESCRIPTION", nullable = false)
  private String descriptionName;

  /** The name of the icon. */
  @Column(name = "ICON", nullable = false)
  private String iconName;

  /** The description. */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "DESCRIPTION", insertable = false, updatable = false)
  private Description description;

  /** The icon. */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ICON", insertable = false, updatable = false)
  private Icon icon;

  /**
   * Set the description, and the description name if description is not null.
   * 
   * @param description
   *          the description
   */
  public void setDescription(final Description description) {
    this.description = description;
    if (this.description != null) {
      this.descriptionName = this.description.getName();
    }
  }

  /**
   * Set the icon, and the icon name if icon is not null.
   * 
   * @param icon
   *          the icon
   */
  public void setIcon(final Icon icon) {
    this.icon = icon;
    if (this.icon != null) {
      this.iconName = this.icon.getName();
    }
  }

  /**
   * @see AbstractIdEntity#appendId(ToStringBuilder)
   */
  @Override
  protected void appendId(final ToStringBuilder builder) {
    builder.append("messageCode", this.messageCode).append("descriptionName", this.descriptionName).append("iconName",
        this.iconName);
    if (this.description != null && Description.class.equals(this.description.getClass())) {
      builder.append("description", this.description);
    }
    if (this.icon != null && Icon.class.equals(this.icon.getClass())) {
      builder.append("icon", this.icon);
    }
  }

}
