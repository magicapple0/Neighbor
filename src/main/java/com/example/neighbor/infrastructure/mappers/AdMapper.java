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
        var ids = new String[imgs.size()];
        for (var i = 0; i < imgs.size(); i++)
            ids[i] = String.valueOf(imgs.get(i).getId());
        return new AdDTO(String.valueOf(ad.getId()),
                ad.getPrice(),
                ad.getOwner().getLogin(),
                ad.getTitle(),
                ad.getDescription(),
                ids,
                ad.getCategory());
    }
    public abstract Ad CreateAdDTOToAd(CreateAdDTO ad);
}
