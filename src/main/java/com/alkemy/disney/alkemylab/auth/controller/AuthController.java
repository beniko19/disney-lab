package com.alkemy.disney.alkemylab.auth.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.alkemy.disney.alkemylab.auth.repository.UserRepository;
import com.alkemy.disney.alkemylab.auth.dto.UserBasicDTO;
import com.alkemy.disney.alkemylab.auth.dto.UserDTO;
import com.alkemy.disney.alkemylab.auth.response.MessageResponse;
import com.alkemy.disney.alkemylab.auth.dto.UserResponseDTO;
import com.alkemy.disney.alkemylab.auth.security.jwt.JwtUtils;
import com.alkemy.disney.alkemylab.auth.security.service.UserDetailsImpl;
import com.alkemy.disney.alkemylab.auth.security.service.UserDetailsServiceImpl;
import com.alkemy.disney.alkemylab.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserBasicDTO userBasicDTO) {
        Authentication authentication = userDetailsService.getAuthentication(userBasicDTO);
        UserDetailsImpl userDetails = userDetailsService.getUserDetails(authentication);
        ResponseCookie jwtCookie = userDetailsService.getResponseCookie(userDetails);
        List<String> roles = userDetailsService.getRoles(userDetails);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserResponseDTO(
                        userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO signUpRequest) {
        userDetailsService.registerUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = userDetailsService.getCleanCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
