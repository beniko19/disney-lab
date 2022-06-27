package com.alkemy.disney.alkemylab.auth.security.service;

import com.alkemy.disney.alkemylab.auth.dto.UserBasicDTO;
import com.alkemy.disney.alkemylab.auth.entity.EnumRoleEntity;
import com.alkemy.disney.alkemylab.auth.entity.RoleEntity;
import com.alkemy.disney.alkemylab.auth.entity.UserEntity;
import com.alkemy.disney.alkemylab.auth.repository.RoleRepository;
import com.alkemy.disney.alkemylab.auth.repository.UserRepository;
import com.alkemy.disney.alkemylab.auth.dto.UserDTO;
import com.alkemy.disney.alkemylab.auth.security.jwt.JwtUtils;
import com.alkemy.disney.alkemylab.exception.EmailAlreadyTaken;
import com.alkemy.disney.alkemylab.exception.RoleNotFound;
import com.alkemy.disney.alkemylab.exception.UsernameAlreadyTaken;
import com.alkemy.disney.alkemylab.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    EmailService emailService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(userEntity);
    }

    @Transactional
    public void registerUser(UserDTO signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            throw new UsernameAlreadyTaken("Error: Username is already taken");
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new EmailAlreadyTaken("Error: Email is already in use!");
        // Create new user's account
        UserEntity userEntity = new UserEntity(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleEntity> roleEntities = new HashSet<>();
        if (strRoles == null) {
            RoleEntity userRoleEntity = roleRepository.findByName(EnumRoleEntity.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFound("Error: Role is not found."));
            roleEntities.add(userRoleEntity);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleEntity adminRoleEntity = roleRepository.findByName(EnumRoleEntity.ROLE_ADMIN)
                                .orElseThrow(() -> new RoleNotFound("Error: Role is not found."));
                        roleEntities.add(adminRoleEntity);
                        break;
                    case "mod":
                        RoleEntity modRoleEntity = roleRepository.findByName(EnumRoleEntity.ROLE_MODERATOR)
                                .orElseThrow(() -> new RoleNotFound("Error: Role is not found."));
                        roleEntities.add(modRoleEntity);
                        break;
                    default:
                        RoleEntity userRoleEntity = roleRepository.findByName(EnumRoleEntity.ROLE_USER)
                                .orElseThrow(() -> new RoleNotFound("Error: Role is not found."));
                        roleEntities.add(userRoleEntity);
                }
                userEntity.setRoles(roleEntities);
            });
        }
        userRepository.save(userEntity);
        emailService.sendWelcomeEmailTo(userEntity.getEmail());
    }


    public Authentication getAuthentication(UserBasicDTO userBasicDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userBasicDTO.getUsername(), userBasicDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public UserDetailsImpl getUserDetails(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails;
    }

    public ResponseCookie getResponseCookie(UserDetailsImpl userDetails) {
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        return jwtCookie;
    }

    public List<String> getRoles(UserDetailsImpl userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return roles;
    }

    public ResponseCookie getCleanCookie() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return cookie;
    }
}
