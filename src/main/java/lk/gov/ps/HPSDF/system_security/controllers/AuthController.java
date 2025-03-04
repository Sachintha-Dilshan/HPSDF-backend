package lk.gov.ps.HPSDF.system_security.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.system_security.models.ERole;
import lk.gov.ps.HPSDF.system_security.models.Role;
import lk.gov.ps.HPSDF.system_security.payload.request.LoginRequest;
import lk.gov.ps.HPSDF.system_security.payload.request.SignupRequest;
import lk.gov.ps.HPSDF.system_security.payload.response.JwtResponse;
import lk.gov.ps.HPSDF.system_security.payload.response.MessageResponse;
import lk.gov.ps.HPSDF.system_security.repositories.RoleRepository;
import lk.gov.ps.HPSDF.system_security.repositories.UserRepository;
import lk.gov.ps.HPSDF.system_security.security.jwt.JwtUtils;
import lk.gov.ps.HPSDF.system_security.models.User;
import lk.gov.ps.HPSDF.system_security.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "chairman":
                        Role chairmanRole = roleRepository.findByName(ERole.ROLE_CHAIRMAN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(chairmanRole);

                        break;
                    case "secretary":
                        Role secretaryRole = roleRepository.findByName(ERole.ROLE_SECRETARY)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(secretaryRole);

                        break;
                    case "hrAdmin":
                        Role hrAdminRole = roleRepository.findByName(ERole.ROLE_HR_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(hrAdminRole);

                        break;
                    case "leaveAdmin":
                        Role leaveAdminRole = roleRepository.findByName(ERole.ROLE_HR_LEAVE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(leaveAdminRole);

                        break;
                    case "archivist":
                        Role archivist = roleRepository.findByName(ERole.ROLE_ARCHIVIST)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(archivist);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
