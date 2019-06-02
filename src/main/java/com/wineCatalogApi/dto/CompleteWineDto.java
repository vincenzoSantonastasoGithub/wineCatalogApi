package com.wineCatalogApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "Complete Wine Object", description = "Wine with relative bottles data")
public class CompleteWineDto {

    WineDto wine;

    @ApiModelProperty(value = "The wine bottles list")
    List<BottleDto> bottleDtoList;

    public WineDto getWine() {
        return wine;
    }

    public void setWine(WineDto wine) {
        this.wine = wine;
    }

    public List<BottleDto> getBottleDtoList() {
        return bottleDtoList;
    }

    public void setBottleDtoList(List<BottleDto> bottleDtoList) {
        this.bottleDtoList = bottleDtoList;
    }
}
