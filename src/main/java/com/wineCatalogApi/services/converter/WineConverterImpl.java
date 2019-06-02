package com.wineCatalogApi.services.converter;

import com.wineCatalogApi.domain.Bottle;
import com.wineCatalogApi.domain.Wine;
import com.wineCatalogApi.domain.WineType;
import com.wineCatalogApi.dto.*;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WineConverterImpl implements WineConverter {

    @Override
    public WineTypeDto convert (WineType wineType) {
        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setId(wineType.getId());
        wineTypeDto.setName(wineType.getName());
        wineTypeDto.setDescription(wineType.getDescription());
        return wineTypeDto;
    }

    @Override
    public Wine convert(WineRequestDto wineRequestDto) {
        Wine wine = new Wine();
        wine.setId(UUID.randomUUID().toString());
        wine.setName(wineRequestDto.getName());
        wine.setDescription(wineRequestDto.getDescription());
        wine.setBottleList(wineRequestDto.getBottleDtoList().stream().map(b -> convert(b)).collect(Collectors.toList()));
        return wine;
    }

    @Override
    public WineDto convert (Wine wine) {
        WineDto wineDto = new WineDto();
        wineDto.setId(wine.getId());
        wineDto.setName(wine.getName());
        wineDto.setDescription(wine.getDescription());
        wineDto.setType(convert(wine.getType()));
        return wineDto;
    }

    @Override
    public BottleDto convert (Bottle bottle) {
        BottleDto bottleDto = new BottleDto();
        bottleDto.setBottleCode(bottle.getBottleCode());
        bottleDto.setPrice(bottle.getPrice());
        return bottleDto;
    }

    @Override
    public Bottle convert (BottleDto bottleDto) {
        Bottle bottle = new Bottle();
        bottle.setBottleCode(bottleDto.getBottleCode());
        bottle.setPrice(bottleDto.getPrice());
        return bottle;
    }

    @Override
    public CompleteWineDto convertComplete(Wine wine) {
        CompleteWineDto completeWineDto = new CompleteWineDto();
        completeWineDto.setWine(convert(wine));
        completeWineDto.setBottleDtoList(wine.getBottleList().stream().map(b -> convert(b)).collect(Collectors.toList()));
        return completeWineDto;
    }
}
