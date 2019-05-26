/*
 *
 */
package fjs.co.graphql.service.impl;

import java.util.Optional;

import fjs.co.graphql.base.AbstractService;
import fjs.co.graphql.dto.User;
import fjs.co.graphql.entity.UserEntity;
import fjs.co.graphql.exception.InvalidCredentialsException;
import fjs.co.graphql.repository.UserRepository;
import fjs.co.graphql.security.jwt.JwtTokenUtil;
import fjs.co.graphql.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import lombok.extern.java.Log;

/**
 *
 * @author quan-ppm
 * @version 1.0
 */
@Service(value = "userService")
@Log
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

	@GraphQLMutation(name = "signin")
    public String signin(@GraphQLArgument(name = "username") String username,
                         @GraphQLArgument(name = "password") String password) throws InvalidCredentialsException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(user.isPresent()) {
            if (encoder.matches(password, user.get().getPassword())) {
                log.info("success...");
                return jwtTokenUtil.generateToken(user.get().getUsername());
            } else {
                log.info("Invalid Credentials1");
                throw new InvalidCredentialsException("Invalid Credentials!");
            }
        }else{
            log.info("Invalid Credentials2");
            throw new InvalidCredentialsException("Invalid Credentials!");
        }
    }

	@Override
	public User findByUsername(String username) {
		return null;
	}

}
