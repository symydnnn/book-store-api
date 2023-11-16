package com.bookstoreapi.service;

import com.bookstoreapi.dao.LoginRequest;
import com.bookstoreapi.dao.LoginResponse;
import com.bookstoreapi.dao.UserDAO;
import com.bookstoreapi.entity.User;

public interface IUserService {

    LoginResponse login(LoginRequest loginRequest);

    UserDAO save(UserDAO userDAO);

    User getByUserEmail(String email);


}
