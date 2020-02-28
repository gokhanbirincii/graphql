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
public class Outfit {

  @Id
  private String id;

  private String size;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "character_id")
  private Character character;

}
