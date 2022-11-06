package com.example.neighbor.controllers;

import com.example.neighbor.services.ImageService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("img")
public class ImageController {
    ImageService imageService;

    @GetMapping("get/{id}")
    byte[] getImage(Long id) throws ChangeSetPersister.NotFoundException {
        var image = imageService.getImage(id);
        if (image.isEmpty())
            throw new ChangeSetPersister.NotFoundException();
        return image.get().getData();
    }
}
