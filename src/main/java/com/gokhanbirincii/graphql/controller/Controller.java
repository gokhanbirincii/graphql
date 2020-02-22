package com.gokhanbirincii.graphql.controller;

import com.gokhanbirincii.graphql.config.GraphqlConfig;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@RestController
@RequestMapping("graphql")
public class Controller {

  @Autowired
  private GraphqlConfig graphqlConfig;

  @PostMapping
  public ResponseEntity<Object> graphqlEndpoint(@RequestBody String body) {
    final ExecutionResult execute = graphqlConfig.graphQL().execute(body);
    return ResponseEntity.ok(execute);
  }
}
