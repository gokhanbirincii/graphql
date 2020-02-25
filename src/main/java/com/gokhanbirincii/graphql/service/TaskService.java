package com.gokhanbirincii.graphql.service;

import com.gokhanbirincii.graphql.domain.Task;
import com.gokhanbirincii.graphql.enm.Status;
import com.gokhanbirincii.graphql.enm.Type;
import com.gokhanbirincii.graphql.repository.TaskRepo;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import io.leangen.graphql.spqr.spring.util.ConcurrentMultiRegistry;
import java.util.Collection;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@GraphQLApi
@Service
public class TaskService {

  private final TaskRepo repo;
  private final ConcurrentMultiRegistry<String, FluxSink<Task>> subscribers = new ConcurrentMultiRegistry<>();

  public TaskService(TaskRepo repo) {
    this.repo = repo;
  }

  @GraphQLMutation
  public Task createTask(String projectCode, String description, @GraphQLArgument(name = "status", defaultValue = "\"PLANNING\"") Status status, Type type) {
    return repo.saveTask(projectCode, description, status, type);
  }

  @GraphQLMutation
  public Task updateTask(@GraphQLNonNull String code, String description, @GraphQLNonNull Status status) {
    Task task = repo.byCode(code);
    if (description != null) {
      task.setDescription(description);
    }
    task.setStatus(status);
    //Notify all the subscribers following this task
    subscribers.get(code).forEach(subscriber -> subscriber.next(task));
    return task;
  }

  @GraphQLQuery
  public Collection<Task> tasks(String projectCode, Status... statuses) {
    return repo.byProjectCodeAndStatus(projectCode, statuses);
  }

  @GraphQLQuery
  public Task task(String code) {
    return repo.byCode(code);
  }

  @GraphQLSubscription
  public Publisher<Task> taskStatusChanged(String code) {
    return Flux.create(subscriber -> subscribers
        .add(code,
            subscriber.onDispose(() -> subscribers.remove(code, subscriber))),
        FluxSink.OverflowStrategy.LATEST);
  }
}
