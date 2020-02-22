package com.gokhanbirincii.graphql.repository;

import com.gokhanbirincii.graphql.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, String> {

  public Character findByName(String name);

}
