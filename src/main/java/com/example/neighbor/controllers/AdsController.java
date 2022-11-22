package com.example.neighbor.controllers;

import com.example.neighbor.dto.AdDTO;
import com.example.neighbor.dto.CreateAdDTO;
import com.example.neighbor.infrastructure.mappers.AdMapper;
import com.example.neighbor.models.Image;
import com.example.neighbor.services.AdsService;
import com.example.neighbor.services.ImageService;
import com.example.neighbor.services.UserDetailsServiceImpl;
import com.example.neighbor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Controller
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final AdMapper adMapper;
    private final ImageService imageService;
    private final UserService userService;

    @Autowired
    public AdsController(AdsService announcementService, AdMapper adMapper, ImageService imageService, UserService userService) {
        this.adsService = announcementService;
        this.adMapper = adMapper;
        this.imageService = imageService;
        this.userService = userService;
    }

    @GetMapping(value = "/getbyid/{id}")
    @ResponseBody
    public AdDTO get(@PathVariable long id){
        return adMapper.AdToAdDTO(adsService.getById(id));
    }

    @PostMapping(value = "/add", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public AdDTO add(Authentication auth, @RequestPart("Ad") CreateAdDTO createAdDTO, @RequestPart("Image") MultipartFile file){
        var ad = adMapper.CreateAdDTOToAd(createAdDTO);
        byte[] bytes;
        try {
            bytes = file.getBytes();
        }
        catch (IOException ignored) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "dab dab");
        }
        var image = imageService.createImage(bytes);
        ad.setImages(new Image[]{image});
        ad.setOwner(userService.getUser(auth.getName()));
        var repoAd = adsService.create(ad);
        return adMapper.AdToAdDTO(repoAd);
    }
}
