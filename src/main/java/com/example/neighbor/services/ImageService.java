package com.example.neighbor.services;

import com.example.neighbor.models.Image;
import com.example.neighbor.repositories.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository repository;

    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    public Image createImage(byte[] data) {
        var image = new Image();
        image.setData(data);
        image = repository.save(image);
        return image;
    }

    public void deleteImage(Long id) {
        repository.deleteById(id);
    }

    public Optional<Image> getImage(Long id) {
        return repository.findById(id);
    }

    public List<Image> createImagesFromMultipartFile(List<MultipartFile> filesList) {
        var imagesContent = new ArrayList<byte[]>();
        try {
            for (MultipartFile image : filesList) imagesContent.add(image.getBytes());
        } catch (IOException ignored) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect data");
        }
        var images = new ArrayList<Image>();
        for (byte[] imageContent : imagesContent) {
            images.add(createImage(imageContent));
        }
        return images;
    }
}
