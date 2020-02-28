package com.gokhanbirincii.graphql.config;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created on February, 2020
 *
 * @author gokhan
 */
@Configuration
@EnableJpaRepositories(value = "com.gokhanbirincii", repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
public class EntityGraphConfig {
}
