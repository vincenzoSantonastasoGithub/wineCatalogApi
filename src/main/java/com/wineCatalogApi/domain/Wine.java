package com.wineCatalogApi.domain;

import java.util.List;

public class Wine {
    String id;
    String name;
    String description;
    WineType type;
    List<Bottle> bottleList;

    public Wine() {
    }

    public Wine(String id, String name, String description, WineType type, List<Bottle> bottleList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.bottleList = bottleList;
    }

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

    public WineType getType() {
        return type;
    }

    public void setType(WineType type) {
        this.type = type;
    }

    public List<Bottle> getBottleList() {
        return bottleList;
    }

    public void setBottleList(List<Bottle> bottleList) {
        this.bottleList = bottleList;
    }
}
