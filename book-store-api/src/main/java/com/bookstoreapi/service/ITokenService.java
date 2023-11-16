package com.bookstoreapi.service;

import com.bookstoreapi.dao.LoginResponse;
import com.bookstoreapi.entity.User;

public interface ITokenService {

    LoginResponse getActiveUser(User user);

    LoginResponse save(User user);

    void inactiveByUserId(Long userId);


}
