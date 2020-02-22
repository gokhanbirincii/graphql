package com.gokhanbirincii.graphql.fetcher;

import com.gokhanbirincii.graphql.entity.Character;
import com.gokhanbirincii.graphql.repository.CharacterRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@Component
public class AllCharactersDataFetcher implements DataFetcher<List<Character>> {

  @Autowired
  private CharacterRepository characterRepository;

  @Override
  public List<Character> get(DataFetchingEnvironment environment) {
    return characterRepository.findAll();
  }
}
