package com.mqdvr.graphql.service;

import com.mqdvr.graphql.dto.UserDto;

public interface UserService {

    UserDto findByUsername(String username);
}
