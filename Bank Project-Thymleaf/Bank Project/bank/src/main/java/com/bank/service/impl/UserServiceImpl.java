package com.bank.service.impl;

import com.bank.exception.CustomJwtException;
import com.bank.exception.EntityNotFoundException;
import com.bank.model.Role;
import com.bank.model.User;
import com.bank.repository.UserRepository;
import com.bank.security.JwtTokenProvider;
import com.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    //Bu class hassleme mekanizmasını çalıştırır. encode() metotdu ile

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    //    @PostConstruct
//    private void postConstruct() {
//        // Sample test admin user insert
//        User admin = new User();
//        admin.setUsername("admin-rmzn");
//        admin.setPassword("pass12345");
//        admin.setEmail("admin@email.com");
//        admin.setRoles(Collections.singletonList(roleRepository.getById(1)));
//        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//        userRepository.save(admin);
//    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public String signin(String userName, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
//            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
            // userRepository.findByUsername(userName).getRoles() bu userin rollerini alarak bana bir toke n creat et diyoruz
            return jwtTokenProvider.createToken(userName, userRepository.findByUsername(userName).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomJwtException("Invalid username/password supplied", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String signup(User user, boolean isAdmin) {
        // (!userRepository.existsByUsername(user.getUsername()) user name daha önceden kullanılmamış ise if bloğuna gir diyor
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
//          Optional<Role> relatedRole = roleRepository.findByName(isAdmin ? "ROLE_ADMIN" : "ROLE_USER");
            Role role = isAdmin ? Role.ROLE_ADMIN : Role.ROLE_CLIENT;
            user.setRoles(Collections.singletonList(role));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomJwtException("Username is already in use", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(String username) {
        User byUsername = userRepository.findByUsername(username);
        if (byUsername == null) {
            //throw new EntityNotFoundException("User", "username : " + username);
        } else if (byUsername.getRoles().contains(Role.ROLE_ADMIN)) {
            throw new AccessDeniedException("No permission to delete user : " + username);
        }
        userRepository.deleteByUsername(username);
    }

    @Override
    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomJwtException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }



  /*  public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }*/

    @Override
    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }

}
