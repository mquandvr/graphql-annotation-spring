/*
 * File: AbstractService.java
 *
 * Copyright (c) 2019
 */
package fjs.co.graphql.base;

import javax.servlet.http.HttpServletRequest;

import io.leangen.graphql.execution.ResolutionEnvironment;

/**
 *
 * @author quan-ppm
 * @version 1.0
 */
public abstract class AbstractService {

    /**
     * Check user in role
     * @param template
     * @param params
     * @return format.
     */
    protected final boolean isUserInRole(ResolutionEnvironment env) {
        HttpServletRequest request = env.dataFetchingEnvironment.getContext();


        return false;
    }

}
