package com.raf.fwk.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

/**
 * The persistent class for the ICON database table.
 * 
 * @author RAF
 */
@Entity
@Table(name = "ICON", schema = "FWK")
@NoArgsConstructor
public class Icon extends AbstractIconEntity {

  /** Serial UID. */
  private static final long serialVersionUID = 5903074137743088156L;

}
