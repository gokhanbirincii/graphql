package com.gokhanbirincii.graphql.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.gokhanbirincii.graphql.entity.Character;
import org.springframework.stereotype.Repository;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@Repository
public interface CharacterRepository extends EntityGraphJpaRepository<Character, String> {

}
