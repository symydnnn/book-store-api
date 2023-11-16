package com.bookstoreapi.service;

import com.bookstoreapi.dao.LoginRequest;
import com.bookstoreapi.dao.LoginResponse;
import com.bookstoreapi.dao.UserDAO;
import com.bookstoreapi.entity.User;
import com.bookstoreapi.enums.MessageTypeEnums;
import com.bookstoreapi.exception.UserAlreadyExistsException;
import com.bookstoreapi.exception.UserNotFoundException;
import com.bookstoreapi.exception.WrongRequestParametersException;
import com.bookstoreapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ITokenService userLoginService;
    private final ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {

        if (ObjectUtils.isEmpty(loginRequest.getEmail()) || ObjectUtils.isEmpty(loginRequest.getPassword())) {
            throw new WrongRequestParametersException(MessageTypeEnums.WRONG_REQUEST_PARAMETERS.getMessage()
                    + " Email or Password info should not be empty.");
        }
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElse(null);

        if (ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException(MessageTypeEnums.USER_NOT_FOUND.getMessage());
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new WrongRequestParametersException(MessageTypeEnums.WRONG_REQUEST_PARAMETERS.getMessage()
                    + " Email or Password info is not right.");
        }

        LoginResponse loginResponse = userLoginService.getActiveUser(user);
        if (!ObjectUtils.isEmpty(loginResponse)) {
            userLoginService.inactiveByUserId(user.getId());
        }

        LoginResponse savedLoginResponse = userLoginService.save(user);
        log.info("UserService - getByLoginRequest : user token generated. User email is {} and name is {}", user.getEmail(), user.getName());
        return savedLoginResponse;

    }

    @Override
    public UserDAO save(UserDAO userDAO) {
        if (ObjectUtils.isEmpty(userDAO.getEmail()) || ObjectUtils.isEmpty(userDAO.getPassword())) {
            throw new WrongRequestParametersException(MessageTypeEnums.WRONG_REQUEST_PARAMETERS.getMessage() + " Email or Password info should not be empty.");
        }

        if (userRepository.existsByEmail(userDAO.getEmail())) {
            throw new UserAlreadyExistsException(MessageTypeEnums.USER_ALREADY_EXISTS.getMessage());
        }

        User user = new User();
        user.setEmail(userDAO.getEmail());
        user.setName(userDAO.getName());
        user.setRole(userDAO.getRole());
        user.setPassword(passwordEncoder.encode(userDAO.getPassword()));

        User newUser = userRepository.save(user);
        log.info("UserService - save : user info saved. User email is {} and name is {}", user.getEmail(), user.getName());

        return mapper.convertValue(newUser, UserDAO.class);

    }

    @Override
    public User getByUserEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException(MessageTypeEnums.USER_NOT_FOUND.getMessage());
        }
        return user.get();
    }
}
