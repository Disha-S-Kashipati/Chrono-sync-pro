package com.cloud.ChronoSyncPro.service;

import java.util.Date;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.config.JwtUtils;
import com.cloud.ChronoSyncPro.entity.RefreshToken;
import com.cloud.ChronoSyncPro.entity.UserAuth;
import com.cloud.ChronoSyncPro.repository.RefreshTokenRepository;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

@Service
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtils jwtUtils;
    private final CookieService cookieService;

    // Constructor
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, JwtUtils jwtUtils, CookieService cookieService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtils = jwtUtils;
        this.cookieService = cookieService;
    }

    // 3 minutes (3*60 Seconds)
    @Value("${cookie.expiration_time}")
    private int EXPIRATION_TIME_COOKIE;

    public String getRefreshToken(String refreshToken, HttpServletResponse response) throws RuntimeException {
        Optional<RefreshToken> findByRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);
        if (findByRefreshToken.isPresent()) {
            if (!findByRefreshToken.get().getExpirationDate().before(new Date())) {
                // updating the expiration time for the refresh token in repository
                findByRefreshToken.get()
                        .setExpirationDate(new Date(System.currentTimeMillis() + EXPIRATION_TIME_COOKIE * 1000));
                refreshTokenRepository.save(findByRefreshToken.get());
                UserAuth userAuth = findByRefreshToken.get().getUserAuth();

                // updating Refresh Cookie
                ResponseCookie resCookie = cookieService.createCookie(refreshToken);
                response.addHeader("Set-Cookie", resCookie.toString());

                return jwtUtils.generateToken(toUserDetails(userAuth));
            } else {
                throw new RuntimeException("Refresh Token Expired");
            }
        } else {
            throw new IllegalArgumentException("RefreshToken Not found");
        }
    }

    public void saveRefreshToken(UserAuth userAuth, String refreshToken) {
        refreshTokenRepository.save(createRefreshToken(userAuth, refreshToken));
    }

    public void deleteRefreshTokenById(int id) {
        refreshTokenRepository.deleteById(id);
    }

    public void deleteRefreshUsingRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
    }

    public RefreshToken getRefreshTokenByUser(UserAuth userAuth) {
        Optional<RefreshToken> findByUserAuth = refreshTokenRepository.findByUserAuth(userAuth);
        return findByUserAuth.orElse(null);
    }

    private RefreshToken createRefreshToken(UserAuth userAuth, String refreshToken) {
        RefreshToken token = new RefreshToken();
        token.setRefreshToken(refreshToken);
        token.setExpirationDate(new Date(System.currentTimeMillis() + EXPIRATION_TIME_COOKIE * 1000));
        token.setUserAuth(userAuth);
        return token;
    }

    private UserDetails toUserDetails(UserAuth userAuth) {
        return User.withUsername(userAuth.getEmail())
                .password(userAuth.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(userAuth.getRole().toString())))
                .build();
    }
}
