package com.wineCatalogApi.services.repositories;

import com.wineCatalogApi.domain.Wine;
import com.wineCatalogApi.domain.WineType;
import java.util.List;

public interface WineRepository {

    List<WineType> getWineTypes ();

    WineType getWineTypeById (String id);

    List<Wine> getWineList ();

    Wine getWineById (String id);

    void deleteWineById (String id);

    Wine addWine (Wine wine);

}
