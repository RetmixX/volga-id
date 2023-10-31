package com.retmix.volga.shared.services;

import com.retmix.volga.shared.dto.MessageDTO;
import com.retmix.volga.shared.dto.admin.AccountDTO;
import com.retmix.volga.shared.dto.admin.UpsertAccountDTO;
import com.retmix.volga.shared.handler.exceptions.ObjectNotFoundException;
import com.retmix.volga.shared.handler.exceptions.PermissionException;
import com.retmix.volga.shared.models.Role;
import com.retmix.volga.shared.models.User;
import com.retmix.volga.shared.repositories.UserRepository;
import com.retmix.volga.utils.PasswordHash;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminAccountService {
    private final UserRepository userRepository;
    private final PasswordHash passwordHash;

    public AccountDTO createAccount(UpsertAccountDTO data) throws NoSuchAlgorithmException {
        Role role;
        if (data.isAdmin()) {
            role = Role.ROLE_ADMIN;
        } else {
            role = Role.ROLE_USER;
        }
        User user = User.builder()
                .username(data.getUsername())
                .password(passwordHash.hashPassword(data.getPassword()))
                .role(role)
                .balance(data.getBalance()).build();

        return userRepository.save(user).toAccountDTO();
    }

    public AccountDTO updateAccount(UpsertAccountDTO data, Long id) throws NoSuchAlgorithmException {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        user.setBalance(data.getBalance());
        user.setPassword(passwordHash.hashPassword(data.getPassword()));
        user.setUsername(data.getUsername());
        if (data.isAdmin()) {
            user.setRole(Role.ROLE_ADMIN);
        } else {
            user.setRole(Role.ROLE_USER);
        }

        return userRepository.save(user).toAccountDTO();
    }

    public List<AccountDTO> index() {
        return userRepository.findAll().stream().map(User::toAccountDTO).collect(Collectors.toList());
    }

    public AccountDTO show(Long id) {
        return userRepository.findById(id).map(User::toAccountDTO).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    public MessageDTO delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        userRepository.delete(user);
        return new MessageDTO("User delete");
    }

    public void isAdmin(User user) {
        if (!user.getRole().roleType()) {
            throw new PermissionException("Access Denied");
        }
    }
}
