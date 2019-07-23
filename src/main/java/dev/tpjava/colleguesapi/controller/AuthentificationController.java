package dev.tpjava.colleguesapi.controller;

import dev.tpjava.colleguesapi.controller.dto.AuthentificationInfos;
import dev.tpjava.colleguesapi.controller.dto.CreerCollegueDTO;
import dev.tpjava.colleguesapi.controller.dto.UserDTO;
import dev.tpjava.colleguesapi.entity.User;
import dev.tpjava.colleguesapi.service.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(allowCredentials = "true")
@RestController
public class AuthentificationController {

    @Value("${jwt.expires_in}")
    private Integer EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthentificationController() {
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> authenticate(@RequestBody AuthentificationInfos infos) {

        return this.userRepository.findByUsername(infos.getUsername())
                .filter(user -> passwordEncoder.matches(infos.getPassword(), user.getPassword()))
                .map(user -> {

                    Map<String, Object> infosSupplementairesToken = new HashMap<>();
                    infosSupplementairesToken.put("roles", user.getRoles());

                    String jetonJWT = Jwts.builder()
                            .setSubject(user.getUsername())
                            .addClaims(infosSupplementairesToken)
                            .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
                            .signWith(SignatureAlgorithm.HS512, SECRET)
                            .compact();

                    ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT)
                            .httpOnly(true)
                            .maxAge(EXPIRES_IN)
                            .path("/")
                            .build();

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                            .build();
                })



                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

    }

    @GetMapping(value = "/me")
    public UserDTO getMe() {
        UserDTO userDTO = new UserDTO();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User u = optionalUser.get();
            if (u.getCollegue() != null) {
                userDTO.setFirstName(u.getCollegue().getFirstName());
                userDTO.setLastName(u.getCollegue().getLastName());
                userDTO.setMatricule(u.getCollegue().getMatricule());
            }
            userDTO.setUsername(username);
            userDTO.setRoles(u.getRoles());
        }

        return userDTO;
    }
}
