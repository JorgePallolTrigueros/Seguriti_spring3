package com.clases.security.usuarios.domain.security;

import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.dao.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthSecurityManager implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthSecurityManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userOptional = userRepository.findByUsername(username);

        //si no esta presente lanzar exception
        if(!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        UserEntity user = userOptional.get();

        List<GrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getRol()));

        return new User(user.getUsername(), user.getPassword(), authorityList);
    }
}
