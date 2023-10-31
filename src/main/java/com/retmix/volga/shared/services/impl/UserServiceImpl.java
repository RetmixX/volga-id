package com.retmix.volga.shared.services.impl;

import com.retmix.volga.shared.dto.*;
import com.retmix.volga.shared.handler.exceptions.ObjectNotFoundException;
import com.retmix.volga.shared.handler.exceptions.PermissionException;
import com.retmix.volga.shared.handler.exceptions.TokenInvalidException;
import com.retmix.volga.shared.handler.exceptions.UserNotFoundException;
import com.retmix.volga.shared.models.Role;
import com.retmix.volga.shared.models.User;
import com.retmix.volga.shared.repositories.UserRepository;
import com.retmix.volga.shared.services.UserService;
import com.retmix.volga.utils.PasswordHash;
import com.retmix.volga.utils.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtService;
    private final PasswordHash passwordHash;

    @Override
    public EnterDTO signUp(UpsertUserDTO data) throws NoSuchAlgorithmException {
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(passwordHash.hashPassword(data.getPassword()));
        user.setRole(Role.ROLE_USER);
        String token = jwtService.generateToken(user.toDTO());
        user.setToken(token);
        userRepository.save(user);
        return user.toEnterDTO();
    }

    @Override
    public MessageDTO logout(User user) {
        user.setToken(null);
        userRepository.save(user);
        return new MessageDTO("logout");
    }

    @Override
    public UserDTO show(User user) {
        return user.toDTO();
    }

    @Override
    public UserDTO update(UpsertUserDTO data, User user) {
        user.setPassword(data.getPassword());
        user.setUsername(data.getUsername());
        return userRepository.save(user).toDTO();
    }

    @Override
    public EnterDTO login(LoginDTO data) throws NoSuchAlgorithmException {
        User user = userRepository.findUserByUsername(data.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Invalid username or password"));
        if (passwordHash.checkPassword(data.getPassword(), user.getPassword())) {
            throw new TokenInvalidException("Invalid password or username");
        }
        String token = jwtService.generateToken(user.toDTO());
        user.setToken(token);
        userRepository.save(user);
        return user.toEnterDTO();
    }

    @Override
    public User getUserByToken(String token) {
        String tokenPrepare = prepareToken(token);
        return userRepository.findUserByToken(tokenPrepare).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public ChangeBalanceDTO changeBalance(Long id, User user) {
        User userChangeBalance = userRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("User not found"));

        if (!user.getRole().equals(Role.ROLE_ADMIN)) {
            if (!userChangeBalance.getId().equals(user.getId())) {
                throw new PermissionException("Cannot add balance not self");
            }
        }

        userChangeBalance.setBalance(userChangeBalance.getBalance() + 250_000.0);
        userRepository.save(userChangeBalance);
        return userChangeBalance.changeBalanceDTO();
    }

    private String prepareToken(String token) {
        if (token == null) {
            throw new TokenInvalidException("Invalid token");
        }
        return token.substring(token.indexOf(" ")).trim();
    }
}