package com.example.neighbor.controllers;

import com.example.neighbor.dto.SecurityTokenDTO;
import com.example.neighbor.dto.UserAuthDTO;
import com.example.neighbor.dto.UserPublicDTO;
import com.example.neighbor.dto.UserRegisterDTO;
import com.example.neighbor.infrastructure.mappers.UserMapper;
import com.example.neighbor.models.User;
import com.example.neighbor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    private final JwtEncoder encoder;

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserController(UserService userService, @Qualifier("userMapperImpl") UserMapper mapper, JwtEncoder encoder, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.mapper = mapper;
        this.encoder = encoder;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping(value = "{login}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "user not found")
    public UserPublicDTO getPublicUserInfo(@PathVariable String login) {
        var user = userService.GetUser(login);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        return mapper.userToUserPublicDto(user);
    }

    @PostMapping(value = "login", consumes = "application/json")
    public SecurityTokenDTO login(@RequestBody UserAuthDTO user) {
        return getToken(user);
    }

    @PostMapping("registration")
    public void registration(@RequestBody UserRegisterDTO registerDTO) {
        userService.CreateUser(mapper.userRegisterDtoToUser(registerDTO));
    }

    SecurityTokenDTO getToken(UserAuthDTO user) {
        var details = userDetailsService.loadUserByUsername(user.getLogin());
        var now = Instant.now();
        var expiry = 36000L;
        String scope = details.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(details.getUsername())
                .claim("scope", scope)
                .build();

        var token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        var tokenDTO = new SecurityTokenDTO(
                details.getUsername(),
                details.getAuthorities().stream().findFirst().get().getAuthority(),
                token,
                claims.getExpiresAt().atZone(ZoneId.systemDefault()).toLocalDateTime());

        return tokenDTO;
    }
}
