package com.bookstoreapi.enums;

import static com.bookstoreapi.enums.PermissionEnums.ADMIN_CREATE;
import static com.bookstoreapi.enums.PermissionEnums.ADMIN_DELETE;
import static com.bookstoreapi.enums.PermissionEnums.ADMIN_READ;
import static com.bookstoreapi.enums.PermissionEnums.ADMIN_UPDATE;
import static com.bookstoreapi.enums.PermissionEnums.USER_READ;
import static com.bookstoreapi.enums.PermissionEnums.USER_UPDATE;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
public enum RoleEnums {

    USER(Set.of(USER_READ, USER_UPDATE)),

    ADMIN(Set.of(ADMIN_READ, ADMIN_UPDATE, ADMIN_DELETE, ADMIN_CREATE, USER_READ, USER_UPDATE));

    @Getter
    private final Set<PermissionEnums> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
