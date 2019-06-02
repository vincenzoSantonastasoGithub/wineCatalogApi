package com.wineCatalogApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Bottle", description = "Bottle Data")
public class BottleDto {

    @ApiModelProperty(value = "The bottle's unique code")
    String bottleCode;

    @ApiModelProperty(value = "The bottle's price")
    Double price;

    public String getBottleCode() {
        return bottleCode;
    }

    public void setBottleCode(String bottleCode) {
        this.bottleCode = bottleCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
