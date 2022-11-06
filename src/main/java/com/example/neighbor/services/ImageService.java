package com.example.neighbor.services;

import com.example.neighbor.models.Image;
import com.example.neighbor.repositories.ImageRepository;
import org.springframework.stereotype.Service;

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
}
