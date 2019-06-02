package com.wineCatalogApi.services.repositories;

import com.wineCatalogApi.domain.Wine;
import com.wineCatalogApi.domain.WineType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WineRepositoryImpl implements WineRepository {

    private static List<WineType> wineTypes = Arrays.asList(new WineType("cod1", "Red Wine", "Red Wine Desc"),
            new WineType("cod2", "White Wine", "White Wine Desc"));

    private static List<Wine> wineList = new LinkedList<>(Arrays.asList());

    @Override
    @Transactional(readOnly = true)
    public List<WineType> getWineTypes () {
        return wineTypes;
    }

    @Override
    @Transactional(readOnly = true)
    public WineType getWineTypeById (String id) {
        List<WineType> wineTypeList = wineTypes.stream().filter(w -> w.getId().equals(id)).collect(Collectors.toList());
        return !wineTypeList.isEmpty() ? wineTypeList.get(0) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public Wine getWineById (String id) {
        List<Wine> wines = wineList.stream().filter(w -> w.getId().equals(id)).collect(Collectors.toList());
        return !wines.isEmpty() ? wines.get(0) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wine> getWineList () {
        return wineList;
    }

    @Override
    public void deleteWineById (String id) {
        wineList.removeIf(w -> w.getId().equals(id));
    }

    @Override
    @Transactional
    public Wine addWine (Wine wine) {
        wineList.add(wine);
        return wine;
    }
}
