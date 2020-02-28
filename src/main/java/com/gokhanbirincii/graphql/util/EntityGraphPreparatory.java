package com.gokhanbirincii.graphql.util;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import graphql.language.Field;
import io.leangen.graphql.execution.ResolutionEnvironment;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@Slf4j
public class EntityGraphPreparatory {

  private EntityGraphPreparatory() {
    //
  }

  private static final List<String> CHARACTER_GRAPH = Arrays.asList("location",
      "location.country", "outfits");

  private static EntityGraph getEntityGraph(ResolutionEnvironment env, String rootElement) {
    Map<String, List<Field>> fieldsByName = env.dataFetchingEnvironment.getSelectionSet().get();

    String[] applicableGraphEntries = fieldsByName.keySet().stream()
        .map(f -> f.replaceAll(isNotBlank(rootElement) ? rootElement + "/" : "", "")
            .replace("/", "."))
        .filter(CHARACTER_GRAPH::contains)
        .toArray(String[]::new);
    if (applicableGraphEntries.length > 0) {
      log.info("Entity Graph Entries: " + String.join(",", applicableGraphEntries));
      return EntityGraphUtils.fromAttributePaths(applicableGraphEntries);
    }

    return null;
  }

  public static EntityGraph getEntityGraphForCharacters(ResolutionEnvironment env) {
    return getEntityGraph(env, "characters");
  }
}
