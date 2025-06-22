package com.ecommerce.user_service.service;

import com.ecommerce.user_service.entity.Role;
import com.ecommerce.user_service.entity.RoleName;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName name);
    boolean assignRole(Long id, String roleName);
    boolean revokeRole(Long id, String roleName);
    List<String> getUserRoles(Long id);
}
