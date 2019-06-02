package com.wineCatalogApi.controllers;

import com.wineCatalogApi.config.WineCatalogApplication;
import com.wineCatalogApi.dto.CompleteWineDto;
import com.wineCatalogApi.dto.WineDto;
import com.wineCatalogApi.dto.WineRequestDto;
import com.wineCatalogApi.dto.WineTypeDto;
import com.wineCatalogApi.services.WineService;
import com.wineCatalogApi.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WineCatalogApplication.class)
@WebMvcTest(WineController.class)
public class WineControllerTest {

    private MockMvc mvc;

    @InjectMocks
    WineController subject;

    @MockBean
    private WineService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(subject).build();
    }

    @Test
    public void whenGetWineTypes_thenReturnExpectedWineTypeList()
            throws Exception {

        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setName("typeName");
        wineTypeDto.setDescription("typeDesc");
        List<WineTypeDto> response = new ArrayList<>();
        response.add(wineTypeDto);

        given(
                service.getWineTypes()
        ).willReturn(
                response
        );

        mvc.perform(get("/wine/types")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("typeName")))
                .andExpect(jsonPath("$[0].description", is("typeDesc")));
    }

    @Test
    public void whenGetWineList_thenReturnExpectedWineList()
            throws Exception {
        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setName("typeName");
        wineTypeDto.setDescription("typeDesc");
        WineDto wineDto = new WineDto();
        wineDto.setName("name");
        wineDto.setDescription("desc");
        wineDto.setType(wineTypeDto);
        List<WineDto> response = new ArrayList<>();
        response.add(wineDto);

        given(
                service.getWineList()
        ).willReturn(
                response
        );

        mvc.perform(get("/wine/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].description", is("desc")))
                .andExpect(jsonPath("$[0].type.name", is("typeName")))
                .andExpect(jsonPath("$[0].type.description", is("typeDesc")));
    }

    @Test
    public void givenWineId_whenGetWine_thenReturnExpectedWine()
            throws Exception {
        String id= "UUID";
        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setName("typeName");
        wineTypeDto.setDescription("typeDesc");
        WineDto wineDto = new WineDto();
        wineDto.setName("name");
        wineDto.setDescription("desc");
        wineDto.setType(wineTypeDto);
        CompleteWineDto response = new CompleteWineDto();
        response.setWine(wineDto);

        given(
                service.getWineById(id)
        ).willReturn(
                response
        );
        mvc.perform(get("/wine/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.wine.name", is("name")))
                .andExpect(jsonPath("$.wine.description", is("desc")))
                .andExpect(jsonPath("$.wine.type.name", is("typeName")))
                .andExpect(jsonPath("$.wine.type.description", is("typeDesc")));
    }

    @Test
    public void givenWineRequest_whenAddWine_thenReturnHttpStatusOk()
            throws Exception {
        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setName("typeName");
        wineTypeDto.setDescription("typeDesc");
        WineDto wineDto = new WineDto();
        wineDto.setName("name");
        wineDto.setDescription("desc");
        wineDto.setType(wineTypeDto);
        CompleteWineDto response = new CompleteWineDto();
        response.setWine(wineDto);
        WineRequestDto request = new WineRequestDto();
        request.setName("name");
        request.setDescription("desc");
        request.setTypeCode("code");
        request.setBottleDtoList(Arrays.asList());
        given(
                service.addWine(request)
        ).willReturn(
                response
        );
        mvc.perform(post("/wine/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenWineRequest_whenUpdateWine_thenReturnHttpStatusOk()
            throws Exception {
        String id= "UUID";
        WineTypeDto wineTypeDto = new WineTypeDto();
        wineTypeDto.setName("typeName");
        wineTypeDto.setDescription("typeDesc");
        WineDto wineDto = new WineDto();
        wineDto.setName("name");
        wineDto.setDescription("desc");
        wineDto.setType(wineTypeDto);
        CompleteWineDto response = new CompleteWineDto();
        response.setWine(wineDto);
        WineRequestDto request = new WineRequestDto();
        request.setName("name");
        request.setDescription("desc");
        request.setTypeCode("code");
        request.setBottleDtoList(Arrays.asList());
        given(
                service.updateWine(id, request)
        ).willReturn(
                response
        );
        mvc.perform(put("/wine/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenWineID_whenDeleteWine_thenReturnHttpStatusOk()
            throws Exception {
        String id= "UUID";
        mvc.perform(delete("/wine/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}