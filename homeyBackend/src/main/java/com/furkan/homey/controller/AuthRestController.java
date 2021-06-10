package com.furkan.homey.controller;

import com.furkan.homey.jwt.JwtUtil;
import com.furkan.homey.model.dto.AuthRequest;
import com.furkan.homey.model.dto.TokenDto;
import com.furkan.homey.service.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthRestController extends BaseController {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;
    @PostMapping(LOGIN)
    public TokenDto creteToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            log.error("ex",ex);
            throw new Exception("Kullanıcı adı ve ya Şifre Hatalı", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(jwtUtil.generateToken(userDetails));

        return tokenDto;
    }
}
