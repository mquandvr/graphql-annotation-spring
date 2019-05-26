package fjs.co.graphql.service;

import fjs.co.graphql.dto.User;

public interface UserService {

    User findByUsername(String username);
}
