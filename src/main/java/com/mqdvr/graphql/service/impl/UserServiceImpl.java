package com.mqdvr.graphql.service.impl;

import org.springframework.stereotype.Service;

import com.mqdvr.graphql.dto.UserDto;
import com.mqdvr.graphql.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDto findByUsername(String username) {
        return null;
    }

}
