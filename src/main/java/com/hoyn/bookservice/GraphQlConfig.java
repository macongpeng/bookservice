package com.hoyn.bookservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import graphql.scalars.ExtendedScalars;

@Configuration
public class GraphQlConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder -> builder
                .scalar(ExtendedScalars.GraphQLLong)  // Note: GraphQLLong instead of Long
                .scalar(ExtendedScalars.DateTime)     // Optional: Add if you need DateTime
                .scalar(ExtendedScalars.Object);      // Optional: Add if you need generic Object
    }
}