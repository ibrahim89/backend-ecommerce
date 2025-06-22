package com.ecommerce.user_service.dto.request;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String gender;
    private String phone;
    private String address;
    private boolean isActive;
    private Collection<? extends GrantedAuthority> roles;
}
