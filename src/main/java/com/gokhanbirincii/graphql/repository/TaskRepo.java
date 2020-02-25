package com.gokhanbirincii.graphql.repository;

import com.gokhanbirincii.graphql.domain.Project;
import com.gokhanbirincii.graphql.domain.Task;
import com.gokhanbirincii.graphql.enm.Status;
import com.gokhanbirincii.graphql.enm.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@Repository
public class TaskRepo {

  private final ProjectRepo projectRepo;

  public TaskRepo(ProjectRepo projectRepo) {
    this.projectRepo = projectRepo;
  }

  public Task saveTask(String projectCode, String description, Status status, Type type) {
    Project project = projectRepo.byCode(projectCode);
    String code = projectCode + "-" + (project.getTasks().size() + 1);
    Task saved = new Task(code, description, status, type);
    projectRepo.addTask(projectCode, saved);
    return saved;
  }

  public Task byCode(String code) {
    String projectCode = getProjectCode(code);
    return projectRepo.byCode(projectCode).getTasks().stream()
        .filter(task -> task.getCode().equals(code))
        .findFirst().orElse(null);
  }

  public Collection<Task> byProjectCodeAndStatus(String code, Status... statuses) {
    return projectRepo.byCode(code).getTasks().stream()
        .filter(task -> statuses == null || Arrays
            .stream(statuses).anyMatch(status -> task.getStatus() == status))
        .collect(Collectors.toList());
  }

  public static String getProjectCode(String taskCode) {
    return taskCode.substring(0, taskCode.indexOf("-"));
  }
}
