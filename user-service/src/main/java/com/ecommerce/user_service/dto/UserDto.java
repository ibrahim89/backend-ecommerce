package com.ecommerce.user_service.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;


}
