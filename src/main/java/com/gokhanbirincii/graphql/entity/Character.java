package com.gokhanbirincii.graphql.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Character {

  @Id
  private String name;
  private String status;
}
