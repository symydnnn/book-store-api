package com.bookstoreapi.service;

import com.bookstoreapi.dao.LoginResponse;
import com.bookstoreapi.entity.Token;
import com.bookstoreapi.entity.User;
import com.bookstoreapi.repository.TokenRepository;
import com.bookstoreapi.security.SecurityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TokenService implements ITokenService {

    private final TokenRepository tokenRepository;
    private final SecurityService provider;

    @Override
    public LoginResponse getActiveUser(User user) {
        Token token = tokenRepository.findFirstByUserIdAndIsActiveTrueOrderByIdDesc(user.getId());
        if (!ObjectUtils.isEmpty(token)) {
            return new LoginResponse(token.getToken(), token.getToken());
        }
        return null;
    }

    @Override
    public LoginResponse save(User user) {
        String token = provider.generateToken(user);
        Token tokenObj = tokenRepository.save(Token.builder()
                .user(user)
                .token(token)
                .isActive(true)
                .build());
        return new LoginResponse(tokenObj.getToken(), tokenObj.getToken());
    }

    @Override
    public void inactiveByUserId(Long userId) {
        Token tokenObj = tokenRepository.findFirstByUserIdAndIsActiveTrueOrderByIdDesc(userId);
        if (!ObjectUtils.isEmpty(tokenObj)) {
            tokenObj.setIsActive(false);
            tokenRepository.save(tokenObj);
        }
    }

}
