package com.gokhanbirincii.graphql.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Character {

  @Id
  private String id;

  private String name;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "character")
  private List<Outfit> outfits;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

}
