package com.StudyHub.StudyHub.controller;

import com.StudyHub.StudyHub.security.JwtUtil;
import com.StudyHub.StudyHub.model.RefreshToken;
import com.StudyHub.StudyHub.dto.auth.TokenRefreshRequest;
import com.StudyHub.StudyHub.dto.auth.TokenRefreshResponse;
import com.StudyHub.StudyHub.service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;

    public RefreshTokenController(RefreshTokenService refreshTokenService, JwtUtil jwtUtil) {
        this.refreshTokenService = refreshTokenService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        refreshTokenService.verifyExpiration(refreshToken);

        String newAccessToken = jwtUtil.generateToken(refreshToken.getUsername());

        return ResponseEntity.ok(new TokenRefreshResponse(newAccessToken, requestRefreshToken));
    }
}
