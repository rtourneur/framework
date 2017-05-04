package com.raf.fwk.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

/**
 * The persistent class for the DESCRIPTION database table.
 * 
 * @author RAF
 */
@Entity
@Table(name = "DESCRIPTION", schema = "FWK")
@NoArgsConstructor
public class Description extends AbstractDescriptionEntity {

  /** Serial UID. */
  private static final long serialVersionUID = 5903074137743088156L;

}
