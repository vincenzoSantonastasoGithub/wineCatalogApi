package com.wineCatalogApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "Wine", description = "Wine Data")
public class WineDto implements Serializable {

    @ApiModelProperty(value = "The wine's unique ID", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    String id;

    @NotNull
    @ApiModelProperty(value = "The wine's name")
    String name;

    @ApiModelProperty(value = "The wine's description")
    String description;

    @NotNull
    @ApiModelProperty(value = "The wine's type")
    WineTypeDto type;

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

    public WineTypeDto getType() {
        return type;
    }

    public void setType(WineTypeDto type) {
        this.type = type;
    }
}
