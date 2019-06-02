package com.wineCatalogApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "Add Wine Request", description = "Contains data to add a new wine")
public class WineRequestDto {

    @NotNull
    @ApiModelProperty(value = "The wine's name")
    String name;

    @ApiModelProperty(value = "The wine's description")
    String description;

    @NotNull
    @ApiModelProperty(value = "The wine's type code")
    String typeCode;

    List<BottleDto> bottleDtoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public List<BottleDto> getBottleDtoList() {
        return bottleDtoList;
    }

    public void setBottleDtoList(List<BottleDto> bottleDtoList) {
        this.bottleDtoList = bottleDtoList;
    }
}
