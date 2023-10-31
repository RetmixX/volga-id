package com.retmix.volga.shared.models;

import com.retmix.volga.rent.models.Rent;
import com.retmix.volga.shared.dto.ChangeBalanceDTO;
import com.retmix.volga.shared.dto.EnterDTO;
import com.retmix.volga.shared.dto.UserDTO;
import com.retmix.volga.shared.dto.admin.AccountDTO;
import com.retmix.volga.transport.models.Transport;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String token;
    @Enumerated(EnumType.STRING)
    private Role role;
    private double balance;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rent> rents;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transport> transports;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(id)
                .username(username)
                .role(role.getRoleName()).build();
    }

    public EnterDTO toEnterDTO() {
        return EnterDTO.builder().id(id).username(username).token(token).build();
    }

    public ChangeBalanceDTO changeBalanceDTO() {
        return ChangeBalanceDTO.builder()
                .id(id)
                .username(username)
                .balance(balance).build();
    }

    public AccountDTO toAccountDTO() {
        return AccountDTO.builder()
                .id(id)
                .username(username)
                .role(role.roleType())
                .balance(balance).build();
    }
}
