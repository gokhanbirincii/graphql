package com.gokhanbirincii.graphql.service;

import com.gokhanbirincii.graphql.domain.Project;
import com.gokhanbirincii.graphql.domain.Task;
import com.gokhanbirincii.graphql.repository.ProjectRepo;
import com.gokhanbirincii.graphql.repository.TaskRepo;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@GraphQLApi
@Service
public class ProjectService {

  private final ProjectRepo repo;

  public ProjectService(ProjectRepo repo) {
    this.repo = repo;
  }

  @GraphQLQuery
  public Project project(String code) {
    return repo.byCode(code);
  }

  @GraphQLMutation
  public Project createProject(String name,
      @GraphQLArgument(name = "tags", defaultValue = "[]") List<String> tags) {
    return repo.save(name, tags);
  }

  @GraphQLQuery
  public List<Project> projects(String... tags) {
    return repo.byTags(tags);
  }

  @GraphQLQuery
  public Project project(@GraphQLContext Task task) {
    return project(TaskRepo.getProjectCode(task.getCode()));
  }

  @GraphQLQuery
  public long currentFunding(@GraphQLContext Project project) {
    return ThreadLocalRandom.current().nextInt(1000) * 1000;
  }
}
