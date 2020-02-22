package com.gokhanbirincii.graphql.config;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */

import com.gokhanbirincii.graphql.fetcher.AllCharactersDataFetcher;
import com.gokhanbirincii.graphql.fetcher.CharacterDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class GraphqlConfig {

  @Value("classpath:schemas/characters.graphql")
  private Resource resource;

  private GraphQL graphQL;

  @Autowired
  private AllCharactersDataFetcher allCharactersDataFetcher;

  @Autowired
  private CharacterDataFetcher characterDataFetcher;

  @PostConstruct
  public void buildGraphql() throws IOException {
    final File schemaFile = resource.getFile();

    final TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);

    final RuntimeWiring runtimeWiring = runtimeWiring();
    final GraphQLSchema graphQLSchema = new SchemaGenerator()
        .makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

    graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  private RuntimeWiring runtimeWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type("Query", typeWiring ->
            typeWiring.dataFetcher("allCharacters", allCharactersDataFetcher)
                      .dataFetcher("character", characterDataFetcher))
        .build();
  }

  public GraphQL graphQL() {
    return graphQL;
  }

}
