package com.example.elembase.Controllers;


import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


import com.example.elembase.Services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

// Активируем Mockito для создания модульных тестов.
@ExtendWith(MockitoExtension.class)
public class CatalogControllerTest {


    //Создаём mock объект
    @Mock
    private ProductService productService;

    //Помещает mock шпионов в класс
    @InjectMocks
    private CatalogController catalogController;

    //C помощью mock mvc мы будем тестировать контроллер будто-бы реальными http запросами
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(catalogController).build();

    }

//    @Test
//    void entityInController() throws Exception {
//        when(productService.getProductsCategoryNames().equals(Arrays.asList("l", "w", "operatingTempRange", "ratedVoltageVDC", "tcCode",
//                "cap", "tol", "productId", "series", "chipDimensionsLxW", "heightDimensionT", "temperatureCharacteristics",
//                "ratedVoltageH", "capacitance", "capacitanceTolerance", "individualSpecificationCodeOrLLR", "packing")));
//        verify();
//    }

    @Test
    void buildCatalogPage() throws Exception {
        mockMvc.perform(get("/elemBase/catalog")).andExpect(redirectedUrl("catalog"));
    }



}
