package com.example.quickcart.controllers;

import com.example.quickcart.dto.LoginRequestDto;
import com.example.quickcart.dto.LoginResponseDto;
import com.example.quickcart.dto.SignUpRequestDto;
import com.example.quickcart.dto.SignUpResponseDto;
import com.example.quickcart.models.User;
import com.example.quickcart.services.AuthService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequest) {
        return new ResponseEntity<>(authService.signUp(signUpRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        Pair<User, String> response = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE, response.b);
        return new ResponseEntity<>(from(response.a),headers,HttpStatus.OK);
    }

    private LoginResponseDto from(User user) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setId(user.getId());
        loginResponseDto.setEmail(user.getEmail());
        loginResponseDto.setRoles(user.getRoles());
        return loginResponseDto;
    }
}
