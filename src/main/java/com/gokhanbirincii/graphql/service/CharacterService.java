package com.gokhanbirincii.graphql.service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.gokhanbirincii.graphql.entity.Character;
import com.gokhanbirincii.graphql.repository.CharacterRepository;
import com.gokhanbirincii.graphql.util.EntityGraphPreparatory;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@Service
@GraphQLApi
@AllArgsConstructor
public class CharacterService {

  private final CharacterRepository characterRepository;

  @GraphQLQuery(name = "characters")
  public List<Character> characters(@GraphQLEnvironment ResolutionEnvironment env) {
    EntityGraph entityGraph = EntityGraphPreparatory.getEntityGraphForCharacters(env);
    return (List<Character>) characterRepository.findAll(entityGraph);
  }

}
