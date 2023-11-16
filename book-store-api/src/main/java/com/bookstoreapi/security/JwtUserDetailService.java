package com.bookstoreapi.security;

import com.bookstoreapi.entity.User;
import com.bookstoreapi.enums.MessageTypeEnums;
import com.bookstoreapi.exception.UserNotFoundException;
import com.bookstoreapi.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException(MessageTypeEnums.USER_NOT_FOUND.getMessage());
        }
        return JwtUserDetails.create(user.get());
    }
}
