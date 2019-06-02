package com.wineCatalogApi.services;


import com.wineCatalogApi.config.WinesCatalog;
import com.wineCatalogApi.domain.Wine;
import com.wineCatalogApi.domain.WineType;
import com.wineCatalogApi.dto.CompleteWineDto;
import com.wineCatalogApi.dto.WineDto;
import com.wineCatalogApi.dto.WineRequestDto;
import com.wineCatalogApi.dto.WineTypeDto;
import com.wineCatalogApi.services.converter.WineConverter;
import com.wineCatalogApi.services.repositories.WineRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WinesCatalog.class)
public class WineServiceTest {

    @Autowired
    public WineService subject;

    @MockBean
    WineRepository wineRepository;

    @MockBean
    WineConverter wineConverter;

    @Test
    public void whenGetWineTypes_thenReturnExpectedWineTypesList() {
        WineType wineType = new WineType("id","name","desc");
        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setName("typeName");
        wineTypeDto.setDescription("typeDesc");
        List<WineType> wineTypes = Arrays.asList(wineType);
        given(
                wineRepository.getWineTypes()
        ).willReturn(
                wineTypes
        );
        given(
                wineConverter.convert(Mockito.any(WineType.class))
        ).willReturn(
                wineTypeDto
        );
        List<WineTypeDto> result = subject.getWineTypes();
        assertThat(result.size(), is(equalTo(1)));
        assertThat(result.get(0).getName(), is(equalTo("typeName")));
        assertThat(result.get(0).getDescription(), is(equalTo("typeDesc")));
    }

    @Test
    public void whenGetWineList_thenReturnExpectedWineList() {
        Wine wine = new Wine();
        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setName("typeName");
        wineTypeDto.setDescription("typeDesc");
        WineDto wineDto = new WineDto();
        wineDto.setName("name");
        wineDto.setDescription("desc");
        wineDto.setType(wineTypeDto);
        List<Wine> wines = Arrays.asList(wine);
        given(
                wineRepository.getWineList()
        ).willReturn(
                wines
        );
        given(
                wineConverter.convert(Mockito.any(Wine.class))
        ).willReturn(
                wineDto
        );
        List<WineDto> result = subject.getWineList();
        assertThat(result.size(), is(equalTo(1)));
        assertThat(result.get(0).getName(), is(equalTo("name")));
        assertThat(result.get(0).getDescription(), is(equalTo("desc")));
        assertThat(result.get(0).getType().getName(), is(equalTo("typeName")));
        assertThat(result.get(0).getType().getDescription(), is(equalTo("typeDesc")));
    }

    @Test
    public void givenValidId_whenGetWineById_thenAReturnExpectedWine() throws ValidationException {
        String id = "UUID";
        Wine wine = new Wine();
        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setName("typeName");
        wineTypeDto.setDescription("typeDesc");
        WineDto wineDto = new WineDto();
        wineDto.setName("name");
        wineDto.setDescription("desc");
        wineDto.setType(wineTypeDto);
        CompleteWineDto completeWineDto = new CompleteWineDto();
        completeWineDto.setWine(wineDto);
        given(
                wineRepository.getWineById(id)
        ).willReturn(
                wine
        );
        given(
                wineConverter.convertComplete(wine)
        ).willReturn(
                completeWineDto
        );
        CompleteWineDto result = subject.getWineById(id);
        assertThat(result.getWine().getName(), is(equalTo("name")));
        assertThat(result.getWine().getDescription(), is(equalTo("desc")));
        assertThat(result.getWine().getType().getName(), is(equalTo("typeName")));
        assertThat(result.getWine().getType().getDescription(), is(equalTo("typeDesc")));
    }

    @Test(expected = ValidationException.class)
    public void givenNotValidId_whenGetWineById_thenReturnValidationException() throws ValidationException {
        String id = "UUID";
        given(
                wineRepository.getWineById(id)
        ).willReturn(
                null
        );
        subject.getWineById(id);
    }

    @Test
    public void givenAddWineRequest_whenAddWine_thenAddWineCorrectly() throws ValidationException {
        String id = "UUID";
        WineType wineType = new WineType("id","name","desc");
        WineRequestDto wineRequestDto = new WineRequestDto();
        wineRequestDto.setTypeCode("typeCode");
        Wine wine = new Wine();
        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setName("typeName");
        wineTypeDto.setDescription("typeDesc");
        WineDto wineDto = new WineDto();
        wineDto.setName("name");
        wineDto.setDescription("desc");
        wineDto.setType(wineTypeDto);
        CompleteWineDto completeWineDto = new CompleteWineDto();
        completeWineDto.setWine(wineDto);
        given(
                wineConverter.convert(wineRequestDto)
        ).willReturn(
                wine
        );
        given(
                wineRepository.getWineTypeById(Mockito.any(String.class))
        ).willReturn(
                wineType
        );
        given(
                wineRepository.addWine(Mockito.any(Wine.class))
        ).willReturn(
                wine
        );
        given(
                wineConverter.convertComplete(wine)
        ).willReturn(
                completeWineDto
        );
        CompleteWineDto result = subject.addWine(wineRequestDto);
        assertThat(result, is(equalTo(completeWineDto)));
    }

    @Test(expected = ValidationException.class)
    public void givenNotValidWineType_whenAddWine_thenReturnValidationException() throws ValidationException {
        String id = "UUID";
        Wine wine = new Wine();
        WineRequestDto wineRequestDto = new WineRequestDto();
        given(
                wineConverter.convert(wineRequestDto)
        ).willReturn(
                wine
        );
        given(
                wineRepository.getWineTypeById(Mockito.any(String.class))
        ).willReturn(
                null
        );
        subject.addWine(wineRequestDto);
    }

    @Test
    public void givenUpdateWineRequest_whenUpdateWine_thenUpdateWineCorrectly() throws ValidationException {
        String id = "UUID";
        WineType wineType = new WineType("id","name","desc");
        WineRequestDto wineRequestDto = new WineRequestDto();
        wineRequestDto.setTypeCode("typeCode");
        Wine wine = new Wine();
        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setName("typeName");
        wineTypeDto.setDescription("typeDesc");
        WineDto wineDto = new WineDto();
        wineDto.setName("name");
        wineDto.setDescription("desc");
        wineDto.setType(wineTypeDto);
        CompleteWineDto completeWineDto = new CompleteWineDto();
        completeWineDto.setWine(wineDto);
        given(
                wineRepository.getWineById(id)
        ).willReturn(
                wine
        );
        given(
                wineRepository.getWineTypeById(Mockito.any(String.class))
        ).willReturn(
                wineType
        );
        given(
                wineConverter.convertComplete(wine)
        ).willReturn(
                completeWineDto
        );
        CompleteWineDto result = subject.updateWine(id, wineRequestDto);
        assertThat(result, is(equalTo(completeWineDto)));
    }

    @Test(expected = ValidationException.class)
    public void givenNotValidWineId_whenUpdateWine_thenReturnValidationException() throws ValidationException {
        String id = "UUID";
        Wine wine = new Wine();
        WineRequestDto wineRequestDto = new WineRequestDto();
        given(
                wineRepository.getWineById(id)
        ).willReturn(
                null
        );
        subject.updateWine(id, wineRequestDto);
    }

    @Test(expected = ValidationException.class)
    public void givenNotValidWineType_whenUpdateWine_thenReturnValidationException() throws ValidationException {
        String id = "UUID";
        Wine wine = new Wine();
        WineRequestDto wineRequestDto = new WineRequestDto();
        given(
                wineRepository.getWineById(id)
        ).willReturn(
                wine
        );
        given(
                wineRepository.getWineTypeById(Mockito.any(String.class))
        ).willReturn(
                null
        );
        subject.updateWine(id, wineRequestDto);
    }

    @Test
    public void givenValidWineId_whenDeleteWine_thenDeleteWine() {
        String id = "UUID";
        subject.deleteWine(id);
    }
}
