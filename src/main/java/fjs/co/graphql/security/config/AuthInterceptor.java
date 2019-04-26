/*
 * File: AuthInterceptor.java
 *
 * Copyright (c) 2019
 */
package fjs.co.graphql.security.config;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import fjs.co.graphql.common.Auth;

import org.springframework.security.core.context.SecurityContextHolder;

import io.leangen.graphql.execution.InvocationContext;
import io.leangen.graphql.execution.ResolverInterceptor;
import io.leangen.graphql.metadata.Operation;
import io.leangen.graphql.util.Directives;

/**
 *
 * @author quan-ppm
 * @version 1.0
 */
public class AuthInterceptor implements ResolverInterceptor {

    @Override
    public Object aroundInvoke(InvocationContext context, Continuation continuation) throws Exception {
        // Auth auth =
        // context.getResolver().getExecutable().getDelegate().getAnnotation(Auth.class);
        // GraphQLType list = (GraphQLType) ((GraphQLList)
        // context.getResolutionEnvironment().dataFetchingEnvironment.getFieldType()).getWrappedType();
        // HttpServletRequest request =
        // context.getResolutionEnvironment().dataFetchingEnvironment.getContext();
        // Auth auth =
        // context.getResolver().getExecutable().getDelegate().getAnnotation(Auth.class);
//        context.getResolutionEnvironment().dataFetchingEnvironment.getExecutionStepInfo().getField().getSelectionSet().getSelections()
        Operation operation = Directives.getMappedOperation(
                context.getResolutionEnvironment().dataFetchingEnvironment
                        .getFieldDefinition())
                .get();
        // Auth auth = operation
        // .getApplicableResolver(context.getResolutionEnvironment()
        // .dataFetchingEnvironment.getArguments().keySet()).getExecutable().getDelegate()
        // .getAnnotation(Auth.class);
        Set<String> allNeededRoles = operation.getResolvers().stream()
                .map(res -> res.getExecutable().getDelegate()) // get the underlying method
                .filter(method -> method.isAnnotationPresent(Auth.class))
                .map(method -> method.getAnnotation(Auth.class))
                .flatMap(obj -> Arrays.stream(obj.rolesRequired()))
                .collect(Collectors.toSet());

//        Set<String> allNeededRoles = allAuthAnnotations.stream()
//                .flatMap(auths -> Arrays.stream(auths.rolesRequired()))
//                .collect(Collectors.toSet());

        Set<String> allRoles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                                .map(role -> role.getAuthority())
                                .collect(Collectors.toSet());

        if (allNeededRoles.size() > 0 && !allNeededRoles.containsAll(allRoles)) {
//            throw new IllegalAccessException("Access denied"); // or return null
            return null;
        }
        return continuation.proceed(context);
    }

}
