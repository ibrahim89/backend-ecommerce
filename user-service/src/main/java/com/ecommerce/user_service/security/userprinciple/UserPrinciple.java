package com.ecommerce.user_service.security.userprinciple;

import com.ecommerce.user_service.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@With
@Builder
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserPrinciple implements UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String gender;
    private String phone;
    private String address;
    private Collection<? extends GrantedAuthority> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorityList = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name().name()))
                .collect(Collectors.toList());

        return UserPrinciple.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPasswordHash())
                .gender(user.getGender())
                .phone(user.getPhone())
                .address(user.getAddress())
                .roles(authorityList)
                .build();
    }
}
