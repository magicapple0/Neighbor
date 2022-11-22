package com.example.neighbor.services;

import com.example.neighbor.dto.SecurityTokenDTO;
import com.example.neighbor.dto.UserAuthDTO;
import com.example.neighbor.models.Role;
import com.example.neighbor.models.User;
import com.example.neighbor.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repository;
    private final JwtEncoder encoder;
    private final UserDetailsService userDetailsService;

    public UserService(UserRepository repository, JwtEncoder encoder, UserDetailsService userDetailsService) {
        this.repository = repository;
        this.encoder = encoder;
        this.userDetailsService = userDetailsService;
    }

    public List<User> getAllUsers() {
        var users = new ArrayList<User>();
        repository.findAll().forEach(users::add);
        return users;
    }

    @Transactional
    public User getUser(long id) {
        return repository.findById(id);
    }

    @Transactional
    public User getUser(String login) {
        return repository.findByLogin(login);
    }

    @Transactional
    public User updateUser(User newUser) {
        return repository.save(newUser);
    }

    @Transactional
    public User createUser(User user) {
        if (repository.findByLogin(user.getLogin()) != null)
            throw new EntityExistsException("user exists");
        user.setRole(Role.USER);
        return repository.save(user);
    }

    @Transactional
    public User applyPatchToUser(User user, JsonPatch patch) {
        var mapper = new ObjectMapper();
        var userJson = mapper.valueToTree(user);
        try {
            return mapper.treeToValue(patch.apply(userJson), User.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            return null;
        }
    }

    @Transactional
    public SecurityTokenDTO getToken(UserAuthDTO user) {
        var details = userDetailsService.loadUserByUsername(user.getLogin());
        var now = Instant.now();
        var expiry = 5000L;
        String scope = details.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now).expiresAt(now.plusSeconds(expiry)).subject(details.getUsername()).claim("scope", scope).build();

        var token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new SecurityTokenDTO(details.getUsername(), details.getAuthorities().stream().findFirst().get().getAuthority(), token, claims.getExpiresAt().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

}
