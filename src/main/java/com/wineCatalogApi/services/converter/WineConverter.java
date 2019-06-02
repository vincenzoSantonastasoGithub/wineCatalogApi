package com.wineCatalogApi.services.converter;

import com.wineCatalogApi.domain.Bottle;
import com.wineCatalogApi.domain.Wine;
import com.wineCatalogApi.domain.WineType;
import com.wineCatalogApi.dto.*;

public interface WineConverter {

    WineTypeDto convert (WineType wineType);

    Wine convert (WineRequestDto wineRequestDto);

    WineDto convert (Wine wine);

    BottleDto convert (Bottle bottle);

    Bottle convert(BottleDto bottleDto);

    CompleteWineDto convertComplete(Wine wine);
}
