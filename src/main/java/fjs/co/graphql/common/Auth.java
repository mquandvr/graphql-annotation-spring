/*
 * File: Auth.java
 *
 * Copyright (c) 2019
 */
package fjs.co.graphql.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.leangen.graphql.annotations.types.GraphQLDirective;

/**
 *
 * @author quan-ppm
 * @version 1.0
 */
@GraphQLDirective // Should be mapped as a GraphQLDirective
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD }) // Applicable to methods
public @interface Auth {
    String[] rolesRequired();
}
