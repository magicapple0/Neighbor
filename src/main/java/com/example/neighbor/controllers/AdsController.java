package com.example.neighbor.controllers;

import com.example.neighbor.dto.AdDTO;
import com.example.neighbor.dto.CreateAdDTO;
import com.example.neighbor.dto.PaginationInfo;
import com.example.neighbor.infrastructure.mappers.AdMapper;
import com.example.neighbor.services.AdService;
import com.example.neighbor.services.ImageService;
import com.example.neighbor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ads")
public class AdsController {

    private final AdService adService;
    private final AdMapper adMapper;
    private final ImageService imageService;
    private final UserService userService;

    @Autowired
    public AdsController(AdService announcementService,
                         AdMapper adMapper,
                         ImageService imageService,
                         UserService userService) {
        this.adService = announcementService;
        this.adMapper = adMapper;
        this.imageService = imageService;
        this.userService = userService;
    }

    @GetMapping(value = "/getbyid/{id}")
    @ResponseBody
    public AdDTO get(@PathVariable long id) {
        return adMapper.AdToAdDTO(adService.getById(id));
    }

    @GetMapping(value = "get")
    @ResponseBody
    public PaginationInfo<AdDTO> getPopular(@RequestParam int page, @RequestParam int pageSize) {
        var ads = adService.getAds(page - 1, pageSize);
        var dtos = new ArrayList<AdDTO>();
        for (var ad : ads) dtos.add(adMapper.AdToAdDTO(ad));
        return new PaginationInfo<>(page, pageSize, dtos.size(), ads.getTotalPages(), dtos);
    }

    @GetMapping(value = "getUserAds/{login}")
    @ResponseBody
    public PaginationInfo<AdDTO> getUserAds(@PathVariable String login, @RequestParam int page, @RequestParam int pageSize) {
        var user = userService.getUser(login);
        var ads = adService.getAds(user.getId(), page - 1, pageSize);
        var dtos = new ArrayList<AdDTO>();
        for (var ad : ads) dtos.add(adMapper.AdToAdDTO(ad));
        return new PaginationInfo<>(page, pageSize, dtos.size(), ads.getTotalPages(), dtos);
    }

    @DeleteMapping(value = "{id}/delete")
    @ResponseBody
    public void remove(Authentication auth, @PathVariable long id) {
        var user = userService.getUser(auth.getName());
        var ad = adService.getById(id);
        if (ad.getOwner().getId() != user.getId())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "not your business");
        adService.remove(ad);
    }

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public AdDTO add(Authentication auth,
                     @RequestPart("Ad") CreateAdDTO createAdDTO,
                     @RequestPart("images") List<MultipartFile> filesList) {
        var ad = adMapper.CreateAdDTOToAd(createAdDTO);
        var images = imageService.createImagesFromMultipartFile(filesList);
        ad.setImages(images);
        ad.setOwner(userService.getUser(auth.getName()));
        var repoAd = adService.create(ad);
        return adMapper.AdToAdDTO(repoAd);
    }
}
