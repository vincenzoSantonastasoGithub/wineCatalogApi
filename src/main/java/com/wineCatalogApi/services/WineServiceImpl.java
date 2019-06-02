package com.wineCatalogApi.services;

import com.wineCatalogApi.domain.Wine;
import com.wineCatalogApi.domain.WineType;
import com.wineCatalogApi.dto.WineRequestDto;
import com.wineCatalogApi.dto.WineDto;
import com.wineCatalogApi.dto.WineTypeDto;
import com.wineCatalogApi.dto.CompleteWineDto;
import com.wineCatalogApi.services.converter.WineConverter;
import com.wineCatalogApi.services.repositories.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;
    private final WineConverter wineConverter;

    @Autowired
    public WineServiceImpl(WineRepository wineRepository, WineConverter wineConverter) {
        this.wineRepository = wineRepository;
        this.wineConverter = wineConverter;
    }

    @Override
    public List<WineTypeDto> getWineTypes() {
        List<WineType> wineTypes = wineRepository.getWineTypes();
        return wineTypes.stream().map(w -> wineConverter.convert(w)).collect( Collectors.toList() );
    }

    @Override
    public List<WineDto> getWineList() {
        List<Wine> wineList = wineRepository.getWineList();
        return wineList.stream().map(w -> wineConverter.convert(w)).collect( Collectors.toList() );
    }

    @Override
    public CompleteWineDto getWineById(String id) throws ValidationException  {
        Wine wine = wineRepository.getWineById(id);
        if (wine == null) {
            throw new ValidationException("Wine not found - id : {}");
        }
        return wineConverter.convertComplete(wine);
    }

    @Override
    public CompleteWineDto addWine(WineRequestDto wineRequestDto) throws ValidationException {
        Wine wine = wineConverter.convert(wineRequestDto);
        WineType wineType = wineRepository.getWineTypeById(wineRequestDto.getTypeCode());
        if (wineType == null) {
            throw new ValidationException("Wine Type not found - code : " + wineRequestDto.getTypeCode());
        }
        wine.setType(wineType);
        return wineConverter.convertComplete(wineRepository.addWine(wine));
    }

    @Override
    public CompleteWineDto updateWine(String id, WineRequestDto wineRequestDto) throws ValidationException {
        Wine wine = wineRepository.getWineById(id);
        if (wine == null) {
            throw new ValidationException("Wine not found - id : {}", id);
        }
        WineType wineType = wineRepository.getWineTypeById(wineRequestDto.getTypeCode());
        if (wineType == null) {
            throw new ValidationException("Wine Type not found - code : " + wineRequestDto.getTypeCode());
        }
        updateWineData(wine, wineRequestDto, wineType);
        return wineConverter.convertComplete(wine);
    }

    @Override
    public void deleteWine(String id) {
        wineRepository.deleteWineById(id);
    }

    private void updateWineData(Wine wine, WineRequestDto wineRequestDto, WineType wineType) {
        wine.setName(wineRequestDto.getName());
        wine.setDescription(wineRequestDto.getDescription());
        wine.setType(wineType);
        if (wineRequestDto.getBottleDtoList() != null) {
            wine.setBottleList(wineRequestDto.getBottleDtoList().stream().map(b -> wineConverter.convert(b)).collect(Collectors.toList()));
        }
    }

}
