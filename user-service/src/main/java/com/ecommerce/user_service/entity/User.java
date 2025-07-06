package com.ecommerce.user_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EntityListeners(Auditable.class)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email(message = "Input must be in Email format")
    private String email;

    @JsonIgnore
    @NotNull(message = "Password must not be null")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Column(name = "password_hash")
    private String passwordHash;

    @NotBlank(message = "First name must not be blank")
    @Size(min = 3, max = 100, message = "First name must be between 3 and 100 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Size(min = 3, max = 100, message = "Last name must be between 3 and 100 characters")
    @Column(name = "last_name")
    private String lastName;

    @Pattern(regexp = "^\\+91[0-9]{9,10}$|^0[0-9]{9,10}$", message = "The phone number is not in the correct format")
    @Size(min = 10, max = 13, message = "Phone number must be between 10 and 11 characters")
    @Column(name = "phone_number", unique = true)
    private String phone;

    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @NotBlank(message = "Gender must not be blank")
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "address")
    private String address;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


}

