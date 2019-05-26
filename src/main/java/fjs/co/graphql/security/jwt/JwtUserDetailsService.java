package fjs.co.graphql.security.jwt;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;

import fjs.co.graphql.entity.UserEntity;
import fjs.co.graphql.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Log
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("load user...");
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            String msg = MessageFormat.format("user:: {0}", user.get().getUsername());
            log.info(msg);
            return getJwtUser(user.get());
        } else {
            log.info("user not found");
            //throw new UsernameNotFoundException(String.format("User not found with username '%s'.", username));
            return null;
        }
    }


    public JwtUser getJwtUser(UserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                user.getEmail(),
//                List.of(new SimpleGrantedAuthority(user.getRole().getName().name())),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName().name())),
                user.getEnabled(),
                null
        );
    }
}