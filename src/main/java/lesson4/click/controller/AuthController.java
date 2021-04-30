package lesson4.click.controller;

import lesson4.click.payload.LoginDto;
import lesson4.click.security.JwtProvider;
import lesson4.click.service.MyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    MyAuthService myAuthService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public HttpEntity<?> logIntoService(@RequestBody LoginDto loginDto) {
        try {


            Authentication authenticate = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
            String token = jwtProvider.generateToken(loginDto.getUserName());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Login or password is wrong");
        }
    }
    @GetMapping("/success")
    public HttpEntity<?> me() {
        return ResponseEntity.ok("Salom");
    }
}



