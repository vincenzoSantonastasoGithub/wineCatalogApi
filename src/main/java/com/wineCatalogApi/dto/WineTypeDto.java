package com.wineCatalogApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "WineType", description = "Wine Type")
public class WineTypeDto implements Serializable {

    @NotNull
    @ApiModelProperty(value = "Wine Type unique id")
    String id;

    @ApiModelProperty(value = "Wine Type name")
    String name;

    @ApiModelProperty(value = "Wine Type Description")
    String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}