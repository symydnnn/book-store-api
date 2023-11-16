package com.bookstoreapi.dao;

import com.bookstoreapi.enums.RoleEnums;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDAO {

    Long id;
    String name;
    String email;
    String password;
    RoleEnums role;
    Date createdAt;
    Date updatedAt;

}
