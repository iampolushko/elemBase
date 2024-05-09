package com.example.elembase.Services;

import com.example.elembase.Entitity.Product;
import com.example.elembase.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public List<String> getAllProductsNames(){

        List<Product> allProducts = productRepo.findAll();
        List<String> productsNames = new ArrayList<>();
        //TODO You should optimize that with product repo method
        for (int x = 0; x < allProducts.size(); x++) {
            Product product = allProducts.get(x);
            String productName = product.getProductId() + product.getSeries() + product.getChipDimensionsLxW()
                    + product.getHeightDimensionT() + product.getTemperatureCharacteristics() + product.getRatedVoltageH()
                    + product.getCapacitance() + product.getCapacitanceTolerance() + product.getIndividualSpecificationCodeOrLLR()
                    + product.getPacking();
            productsNames.add(productName);
        }

        return productsNames;
    }

    public HashMap<String, List<String>> getAllProductsStatsByNames(){
        HashMap<String, List<String>> statsByNames = new HashMap<>();
        List<String> l = productRepo.getAllL();
        List<String> w = productRepo.getAllW();
        List<String> operatingTempRange = productRepo.getAllOperating_temp_range();
        List<String> ratedVoltageVDC = productRepo.getAllRated_voltage_vdc();
        List<String> tcCode = productRepo.getAllTc_code();
        List<String> cap = productRepo.getAllCap();
        List<String> tol = productRepo.getAllTol();

        List<String> names = getAllProductsNames();


        for (int tick = 0; tick < names.size(); tick++) {
                statsByNames.put(names.get(tick), Arrays.asList(l.get(tick), w.get(tick), operatingTempRange.get(tick),
                        ratedVoltageVDC.get(tick), tcCode.get(tick), cap.get(tick), tol.get(tick)));
        }
        return statsByNames;
    }

    public List<String> getProductStatsNames() {
        return Arrays.asList("l", "w", "operatingTempRange", "ratedVoltageVDC", "tcCode", "cap", "tol");
    }

    public List<String> getProductsCategoryNames() {
        return Arrays.asList("l", "w", "operatingTempRange", "ratedVoltageVDC", "tcCode",
                "cap", "tol", "productId", "series", "chipDimensionsLxW", "heightDimensionT", "temperatureCharacteristics",
                "ratedVoltageH", "capacitance", "capacitanceTolerance", "individualSpecificationCodeOrLLR", "packing");
    }

    public HashMap<String, List<String>> getHashFilters() {
        HashMap<String, List<String>> hashFilters = new HashMap<>();
        List<Product> allProducts = productRepo.findAll();
        //replace this arrays.as list to call method getProductsCategoryNames
        List<String> productsCategoryNames = Arrays.asList("l", "w", "operatingTempRange", "ratedVoltageVDC", "tcCode",
                "cap", "tol", "productId", "series", "chipDimensionsLxW", "heightDimensionT", "temperatureCharacteristics",
                "ratedVoltageH", "capacitance", "capacitanceTolerance", "individualSpecificationCodeOrLLR", "packing");

        List<String> l = new ArrayList<>();
        List<String> w = new ArrayList<>();
        List<String> operatingTempRange = new ArrayList<>();
        List<String> ratedVoltageVDC = new ArrayList<>();
        List<String> tcCode = new ArrayList<>();
        List<String> cap = new ArrayList<>();
        List<String> tol = new ArrayList<>();
        List<String> productId = new ArrayList<>();
        List<String> series = new ArrayList<>();
        List<String> chipDimensionsLxW = new ArrayList<>();
        List<String> heightDimensionT = new ArrayList<>();
        List<String> temperatureCharacteristics = new ArrayList<>();
        List<String> ratedVoltageH = new ArrayList<>();
        List<String> capacitance = new ArrayList<>();
        List<String> capacitanceTolerance = new ArrayList<>();
        List<String> individualSpecificationCodeOrLLR = new ArrayList<>();
        List<String> packing = new ArrayList<>();

        List<List<String>> productColumns = Arrays.asList(l, w, operatingTempRange, ratedVoltageVDC, tcCode, cap, tol,
                productId, series, chipDimensionsLxW, heightDimensionT, temperatureCharacteristics, ratedVoltageH,
                capacitance, capacitanceTolerance, individualSpecificationCodeOrLLR, packing);

        for (int x = 0; x < allProducts.size(); x++) {
            l.add(allProducts.get(x).getL());
            w.add(allProducts.get(x).getW());
            operatingTempRange.add(allProducts.get(x).getOperatingTempRange());
            ratedVoltageVDC.add(allProducts.get(x).getRatedVoltageVDC());
            tcCode.add(allProducts.get(x).getTcCode());
            cap.add(allProducts.get(x).getCap());
            tol.add(allProducts.get(x).getTol());
            productId.add(allProducts.get(x).getProductId());
            series.add(allProducts.get(x).getSeries());
            chipDimensionsLxW.add(allProducts.get(x).getChipDimensionsLxW());
            heightDimensionT.add(allProducts.get(x).getHeightDimensionT());
            temperatureCharacteristics.add(allProducts.get(x).getTemperatureCharacteristics());
            ratedVoltageH.add(allProducts.get(x).getRatedVoltageH());
            capacitance.add(allProducts.get(x).getCapacitance());
            capacitanceTolerance.add(allProducts.get(x).getCapacitanceTolerance());
            individualSpecificationCodeOrLLR.add(allProducts.get(x).getIndividualSpecificationCodeOrLLR());
            packing.add(allProducts.get(x).getPacking());
        }

        for (int x = 0; x < productsCategoryNames.size(); x++) {
            hashFilters.put(productsCategoryNames.get(x), productColumns.get(x).stream().distinct().collect(Collectors.toList()));
        }

        return hashFilters;

    }

    //Что-то в этой хуйне пошло не так
    public List<String> getFilteredProductsNames(ArrayList<String> filterParams){
        List<Product> filteredProducts = productRepo.getFilteredProducts(filterParams.get(0), filterParams.get(1),
                filterParams.get(2), filterParams.get(3), filterParams.get(4), filterParams.get(5), filterParams.get(6),
                filterParams.get(7), filterParams.get(8), filterParams.get(9), filterParams.get(10), filterParams.get(11),
                filterParams.get(12), filterParams.get(13), filterParams.get(14), filterParams.get(15), filterParams.get(16));

        List<String > filteredProductsNames = new ArrayList<>();

        for (Product product : filteredProducts) {
            filteredProductsNames.add(product.getProductId() + product.getSeries() + product.getChipDimensionsLxW()
                    + product.getHeightDimensionT() + product.getTemperatureCharacteristics() + product.getRatedVoltageH()
                    + product.getCapacitance() + product.getCapacitanceTolerance() + product.getIndividualSpecificationCodeOrLLR()
                    + product.getPacking());
        }

        return filteredProductsNames;
    }



}
