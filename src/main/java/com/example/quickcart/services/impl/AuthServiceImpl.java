package com.example.quickcart.services.impl;

import com.example.quickcart.dto.SignUpRequestDto;
import com.example.quickcart.dto.SignUpResponseDto;
import com.example.quickcart.exceptions.InvalidTokenException;
import com.example.quickcart.exceptions.UserAlreadyExistsException;
import com.example.quickcart.exceptions.UserNotRegisteredException;
import com.example.quickcart.models.STATUS;
import com.example.quickcart.models.Session;
import com.example.quickcart.models.User;
import com.example.quickcart.repositories.SessionRepository;
import com.example.quickcart.repositories.UserRepository;
import com.example.quickcart.services.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Signature;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SecretKey secretKey;
    private final SessionRepository sessionRepository;

    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SecretKey secretKey, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        this.secretKey = secretKey;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequest) {
        validate(signUpRequest);
        User user = mapSignUpRequestDtoToUser(signUpRequest);
        User savedUser = userRepository.save(user);
        return mapUserToSignUpRequestDto(savedUser);
    }

    @Override
    public Pair<User, String> login(String email, String password) {
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail.isEmpty()) {
            throw new UserNotRegisteredException("User not registered with email: " + email);
        }
        User user = userByEmail.get();
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password for email: " + email);
        }

        Map<String, Object> payload = new HashMap<>();
        long nowInMillis = System.currentTimeMillis();
        payload.put("iat", nowInMillis);
        payload.put("exp", nowInMillis + 100000);
        payload.put("userId", user.getId());
        payload.put("iss", "QUICK_CART");
        payload.put("scope", user.getRoles());

        String token = Jwts.builder().claims(payload).signWith(secretKey).compact();

        Session session = new Session();
        session.setToken(token);
        session.setUser(user);
        session.setStatus(STATUS.ACTIVE);
        sessionRepository.save(session);

        return new Pair<>(user, token);
    }

    @Override
    public Boolean validate(String token, UUID userId) {
        Optional<Session> sessionByToken = sessionRepository.findSessionByToken(token);
        if(sessionByToken.isEmpty()) {
            throw new InvalidTokenException("Invalid token");
        }
        Session session = sessionByToken.get();
        if(session.getStatus() == STATUS.INACTIVE) {
            return false;
        }
        if(session.getUser().getId().equals(userId)) {
            throw new InvalidTokenException("Invalid token");
        }
        JwtParser parser = Jwts.parser().verifyWith(secretKey).build();
        Claims payload = parser.parseSignedClaims(token).getPayload();

        long exp = (long) payload.get("exp");
        long currentTime = System.currentTimeMillis();
        if(exp < currentTime) {
            session.setStatus(STATUS.INACTIVE);
            sessionRepository.save(session);
            return false;
        }
        return true;
    }

    private User mapSignUpRequestDtoToUser(SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        user.setAddress(signUpRequest.getAddress());
        user.setContactNumber(signUpRequest.getContactNumber());
        user.setName(signUpRequest.getFullName());
        return user;
    }

    private void validate(SignUpRequestDto signUpRequest) {
        Boolean isUserExistWithEmail = userRepository.existsUserByEmail(signUpRequest.getEmail());
        if (isUserExistWithEmail) {
            throw new UserAlreadyExistsException("User already exists with email: " + signUpRequest.getEmail());
        }
        Boolean isUserExistWithContactNumber = userRepository.existsUserByContactNumber(signUpRequest.getContactNumber());
        if (isUserExistWithContactNumber) {
            throw new UserAlreadyExistsException("User already exists with contact number: " + signUpRequest.getContactNumber());
        }
    }

    private SignUpResponseDto mapUserToSignUpRequestDto(User user) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setId(user.getId());
        signUpResponseDto.setEmail(user.getEmail());
        signUpResponseDto.setFullName(user.getName());
        signUpResponseDto.setAddress(user.getAddress());
        signUpResponseDto.setContactNumber(user.getContactNumber());
        return signUpResponseDto;
    }
}
