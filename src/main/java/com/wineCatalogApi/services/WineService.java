package com.wineCatalogApi.services;

import com.wineCatalogApi.dto.WineRequestDto;
import com.wineCatalogApi.dto.CompleteWineDto;
import com.wineCatalogApi.dto.WineDto;
import com.wineCatalogApi.dto.WineTypeDto;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface WineService {

    List<WineTypeDto> getWineTypes();

    List<WineDto> getWineList();

    CompleteWineDto getWineById(String id) throws ValidationException;

    CompleteWineDto addWine(WineRequestDto wineRequestDto) throws ValidationException;

    CompleteWineDto updateWine(String id, WineRequestDto wineRequestDto) throws ValidationException;

    void deleteWine(String id);
}
