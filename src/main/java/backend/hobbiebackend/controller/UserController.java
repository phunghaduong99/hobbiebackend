package backend.hobbiebackend.controller;

import backend.hobbiebackend.jwt.HobbieUserDetailsService;
import backend.hobbiebackend.jwt.JwtRequest;
import backend.hobbiebackend.jwt.JwtResponse;
import backend.hobbiebackend.jwt.JwtUtility;
import backend.hobbiebackend.model.dto.AppClientSignUpDto;
import backend.hobbiebackend.model.entities.AppClient;
import backend.hobbiebackend.model.entities.UserEntity;
import backend.hobbiebackend.model.entities.enums.UserRoleEnum;
import backend.hobbiebackend.service.NotificationService;
import backend.hobbiebackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4040")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final HobbieUserDetailsService hobbieUserDetailsService;
    private final JwtUtility jwtUtility;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, HobbieUserDetailsService hobbieUserDetailsService, JwtUtility jwtUtility, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.hobbieUserDetailsService = hobbieUserDetailsService;
        this.jwtUtility = jwtUtility;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    @Operation(summary = "Create new client-user")
    public ResponseEntity<?> signup(@RequestBody AppClientSignUpDto user){
        System.out.println(user);
        if (this.userService.userExists(user.getUsername(), user.getEmail())) {
            throw new RuntimeException("Username or email address already in use.");
        }
        AppClient client = this.userService.register(user);
        return new ResponseEntity<AppClient>(client, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate user and get JWT Token")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            System.out.println("1");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception( e);
        }
        System.out.println("done");
        final UserDetails userDetails
                = hobbieUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);
        return new JwtResponse(token);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    @Operation(summary = "Login based on user role after authentication", security = @SecurityRequirement(name = "bearerAuth"))
    public String logInUser(@RequestParam String username) {
        UserEntity userByUsername = this.userService.findUserByUsername(username);
        if (userByUsername.getRoles().stream()
                .anyMatch(u -> u.getRole().equals(UserRoleEnum.USER))) {
            return "USER";
        } else if (userByUsername.getRoles().stream()
                .anyMatch(u -> u.getRole().equals(UserRoleEnum.BUSINESS_USER))) {
            return "BUSINESS_USER";
        }
        return null;
    }

    @GetMapping("/client")
    @Operation(summary = "show client-user information", security = @SecurityRequirement(name = "bearerAuth"))
    public AppClient showUserDetails(@RequestParam String username) {
        return this.userService.findAppClientByUsername(username);
    }

}
