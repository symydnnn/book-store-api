package com.bookstoreapi.controller;

import com.bookstoreapi.dao.LoginRequest;
import com.bookstoreapi.dao.LoginResponse;
import com.bookstoreapi.dao.UserDAO;
import com.bookstoreapi.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "users", description = "The User API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;


    @Operation(description = "Sign Up A User", tags = {"users"})
    @PostMapping(value = "/signup")
    public ResponseEntity<UserDAO> signup(@RequestBody UserDAO userDAO) {

        UserDAO newUser = userService.save(userDAO);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }


    @Operation(description = "Login A User", tags = {"users"})
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse loginResponse = userService.login(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

}
