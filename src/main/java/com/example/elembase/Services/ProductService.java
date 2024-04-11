package com.example.elembase.Services;

import com.example.elembase.Entitity.Product;
import com.example.elembase.repository.ProductRepo;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<String> getProductsCategoryNames() {
        return Arrays.asList("l", "w", "operatingTempRange", "ratedVoltageVDC", "tcCode",
                "cap", "tol", "productId", "series", "chipDimensionsLxW", "heightDimensionT", "temperatureCharacteristics",
                "ratedVoltageH", "capacitance", "capacitanceTolerance", "individualSpecificationCodeOrLLR", "packing");
    }

    public HashMap<String, List<String>> getHashFilters() {
        HashMap<String, List<String>> hashFilters = new HashMap<>();
        List<Product> allProducts = productRepo.findAll();
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

}
