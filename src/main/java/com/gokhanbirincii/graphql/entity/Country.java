package com.gokhanbirincii.graphql.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Country {

  @Id
  private String id;

  private String countryName;

  private String countryCode;

}
