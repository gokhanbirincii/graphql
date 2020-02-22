package com.gokhanbirincii.graphql.fetcher;

import com.gokhanbirincii.graphql.entity.Character;
import com.gokhanbirincii.graphql.repository.CharacterRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@Component
public class CharacterDataFetcher implements DataFetcher<Character> {

  @Autowired
  private CharacterRepository characterRepository;

  @Override
  public Character get(DataFetchingEnvironment environment) {
//    final String name = String.valueOf(environment.getArgument("name"));
//    return characterRepository.findByName(name);

    return new Character("Gokhan", "Dead");
  }
}
