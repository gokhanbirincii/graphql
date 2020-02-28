package com.gokhanbirincii.graphql.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@Entity
@Getter
@Setter
public class Location {

  @Id
  private String id;

  private String street;

  private String postalCode;

  private String city;

  private String stateProvince;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "country_id")
  private Country country;

}
