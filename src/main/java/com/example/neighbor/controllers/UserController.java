package com.example.neighbor.controllers;

import com.example.neighbor.dto.SecurityTokenDTO;
import com.example.neighbor.dto.UserAuthDTO;
import com.example.neighbor.dto.UserPublicDTO;
import com.example.neighbor.dto.UserRegisterDTO;
import com.example.neighbor.infrastructure.mappers.UserMapper;
import com.example.neighbor.models.Image;
import com.example.neighbor.models.User;
import com.example.neighbor.services.ImageService;
import com.example.neighbor.services.UserService;
import com.github.fge.jsonpatch.JsonPatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    private final ImageService imageService;
    private final Logger logger;

    @Autowired
    public UserController(UserService userService,
                          @Qualifier("userMapperImpl") UserMapper mapper,
                          JwtEncoder encoder,
                          UserDetailsService userDetailsService,
                          ImageService imageService) {
        this.userService = userService;
        this.mapper = mapper;
        this.imageService = imageService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping(value = "{login}")
    @ResponseBody
    //@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "user not found")
    public UserPublicDTO getPublicUserInfo(@PathVariable String login) {
        var user = userService.getUser(login);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        return mapper.userToUserPublicDto(user);
    }

    @PostMapping(value = "login", consumes = "application/json")
    public SecurityTokenDTO login(@RequestBody UserAuthDTO user) {
        return userService.getToken(user);
    }

    @PatchMapping(value = "{login}", consumes = "application/json-patch+json")
    @ResponseBody
    /*@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "user not found")*/
    public UserPublicDTO updateUserInfo(Authentication auth, @RequestBody JsonPatch patch) {
        var user = userService.getUser(auth.getName());
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        var userPatched = userService.applyPatchToUser(user, patch);
        if (userPatched == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unable to apply patch");
        var newUser = userService.updateUser(userPatched);
        return mapper.userToUserPublicDto(newUser);
    }


    @PostMapping(value = "registration")
    @ResponseBody
    public SecurityTokenDTO registration(@RequestPart("AccountRegistration") UserRegisterDTO userRegisterDTO, @RequestPart("avatar") MultipartFile file) {
        Image image;
        try {
            image = imageService.createImage(file.getBytes());
        } catch (IOException e) {
            logger.error("IOException while saving profile avatar");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect avatar", e);
        }

        var user = mapper.userRegisterDtoToUser(userRegisterDTO);
        user.setAvatar(image);
        user = userService.createUser(user);
        return userService.getToken(mapper.userToUserAuthDto(user));
    }

    @GetMapping(value = "myname")
    public String getMyName(Authentication auth) {
        return auth.getName();
    }


}
