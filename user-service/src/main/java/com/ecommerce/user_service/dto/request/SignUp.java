package com.ecommerce.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
public class SignUp {
    @NotBlank(message = "The First must not be left blank")
    @Size(min = 6, max = 50, message = "The first name must be 6 characters or more")
    private String firstName;

    @NotBlank(message = "The Last must not be left blank")
    @Size(min = 6, max = 50, message = "The last name must be 6 characters or more")
    private String lastName;

    @NotBlank(message = "The username must not be left blank")
    @Size(min = 6, max = 50, message = "The username must be 6 characters or more")
    private String username;

    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Password must contain all uppercase and lowercase letters and numbers")
    private String password;

    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Invalid email format")
    private String email;

    @NotBlank(message = "Gender must not be left blank")
    private String gender;

   // @NotBlank(message = "Gender must not be left blank")
    private String address;

    @Size(min = 10, max = 13, message = "Phone number must be between 10 and 11 digits")
    @Pattern(regexp = "^\\+91[0-9]{9,10}$|^0[0-9]{9,10}$", message = "The phone number is not in the correct format")
    private String phone;

    private Set<String> roles;
}
