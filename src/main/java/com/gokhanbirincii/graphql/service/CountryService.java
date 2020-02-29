package com.gokhanbirincii.graphql.service;

import com.gokhanbirincii.graphql.entity.Country;
import com.gokhanbirincii.graphql.repository.CountryRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.leangen.graphql.spqr.spring.util.ConcurrentMultiMap;
import java.util.Collection;
import java.util.UUID;
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
public class CountryService {

  private final CountryRepository repo;
  private final ConcurrentMultiMap<String, FluxSink<Country>> subscribers = new ConcurrentMultiMap<>();

  public CountryService(CountryRepository repo) {
    this.repo = repo;
  }

  @GraphQLMutation
  public Country createCountry(@GraphQLNonNull String countryCode,
      @GraphQLNonNull String countryName) {
    final Country country = new Country(UUID.randomUUID().toString(), countryName, countryCode);
    return repo.save(country);
  }

  @GraphQLMutation
  public Country updateCountry(@GraphQLNonNull String id, String name) {
    Country country = repo.findById(id).orElseThrow(() -> {
      throw new IllegalStateException();
    });

    if (name != null) {
      country.setCountryName(name);
    }

    subscribers.get(id).forEach(subscriber -> subscriber.next(country));
    return country;
  }

  @GraphQLQuery
  public Collection<Country> countries() {
    return repo.findAll();
  }

  @GraphQLSubscription
  public Publisher<Country> countryChanged(String id) {
    return Flux.create(subscriber -> subscribers
            .add(id,
                subscriber.onDispose(() -> subscribers.remove(id, subscriber))),
        FluxSink.OverflowStrategy.LATEST);
  }
}
