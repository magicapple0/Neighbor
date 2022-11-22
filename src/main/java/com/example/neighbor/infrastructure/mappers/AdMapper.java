package com.example.neighbor.infrastructure.mappers;

import com.example.neighbor.dto.AdDTO;
import com.example.neighbor.dto.CreateAdDTO;
import com.example.neighbor.models.Ad;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public abstract class AdMapper {

    public AdDTO AdToAdDTO(Ad ad)
    {
        var imgs = ad.getImages();
        var ids = new long[imgs.length];
        for (var i = 0; i < imgs.length; i++)
            ids[i] = imgs[i].getId();
        return new AdDTO(ad.getId(),
                ad.getOwner().getName(),
                ids,
                ad.getPrice(),
                ad.getTitle(),
                ad.getDescription(),
                ad.getCategory());
    }
    public abstract Ad CreateAdDTOToAd(CreateAdDTO ad);
}
