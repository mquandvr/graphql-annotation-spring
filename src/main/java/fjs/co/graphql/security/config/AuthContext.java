package fjs.co.graphql.security.config;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fjs.co.graphql.entity.UserEntity;

public class AuthContext {

    private final UserEntity user;

    public AuthContext(UserEntity user, Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}