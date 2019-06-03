package com.wineCatalogApi.controllers;

import com.wineCatalogApi.dto.WineRequestDto;
import com.wineCatalogApi.dto.CompleteWineDto;
import com.wineCatalogApi.dto.WineDto;
import com.wineCatalogApi.dto.WineTypeDto;
import com.wineCatalogApi.services.WineService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping(value = "wine", produces = "application/json")
@Api(value = "wine", description = "Wine operations", tags = {"Wine"}, produces = "application/json")
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping(path = "/types", produces = "application/json")
    @ApiOperation(value = "List the type of wines available")
    public List<WineTypeDto> getWineTypeList () {
        return wineService.getWineTypes();
    }

    @GetMapping(path = "/", produces = "application/json")
    @ApiOperation(value = "List the wines available")
    public List<WineDto> getWineList () {
        return wineService.getWineList();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Get wine details with relative bottles data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Wine's unique id", dataType = "String", required = true, paramType = "path"),
    })
    public CompleteWineDto getWine (@PathVariable String id) throws ValidationException {
        return wineService.getWineById(id);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Add a new wine to the catalog")
    public CompleteWineDto addWine (@Valid @RequestBody WineRequestDto wineRequestDto) throws ValidationException {
        return wineService.addWine(wineRequestDto);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Update wine into the catalog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Id of the wine to be updated", dataType = "String", required = true, paramType = "path")
    })
    public CompleteWineDto updateWine (@PathVariable String id, @Valid @RequestBody WineRequestDto wineRequestDto) throws ValidationException {
        return wineService.updateWine(id, wineRequestDto);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a wine from the catalog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Id of the wine to be canceled", dataType = "String", required = true, paramType = "path")
    })
    public void deleteWine (@PathVariable String id) {
        wineService.deleteWine(id);
    }
}
