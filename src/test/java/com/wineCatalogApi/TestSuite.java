package com.wineCatalogApi;

import com.wineCatalogApi.controllers.WineControllerTest;
import com.wineCatalogApi.services.WineServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        WineControllerTest.class,
        WineServiceTest.class
})

public class TestSuite {
}
