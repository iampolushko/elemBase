package com.example.elembase.Entitity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "l")
    private String l;

    @Column(name = "w")
    private String w;

    @Column(name = "operating_temp_range")
    private String operatingTempRange;

    @Column(name = "rated_voltage_vdc")
    private String ratedVoltageVDC;

    @Column(name = "tc_code")
    private String tcCode;

    @Column(name = "cap")
    private String cap;

    @Column(name = "tol")
    private String tol;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "series")
    private String series;

    @Column(name = "chip_dimensions_lxw")
    private String chipDimensionsLxW;

    @Column(name = "height_dimension_t")
    private String heightDimensionT;

    @Column(name = "temperature_characteristics")
    private String temperatureCharacteristics;

    @Column(name = "rated_voltage_h")
    private String ratedVoltageH;

    @Column(name = "capacitance")
    private String capacitance;

    @Column(name = "capacitance_tolerance")
    private String capacitanceTolerance;

    @Column(name = "individual_specification_code_or_llr")
    private String individualSpecificationCodeOrLLR;

    @Column(name = "packing")
    private String packing;

    public String getColumnByNumber(Integer tick){
        if (tick == 0){
            return getL();
        } else if (tick == 1) {
            return getW();
        } else if (tick == 2) {
            return getOperatingTempRange();
        }else if (tick == 3) {
            return getRatedVoltageVDC();
        }else if (tick == 4) {
            return getTcCode();
        }else if (tick == 5) {
            return getCap();
        }else if (tick == 6) {
            return getTol();
        }else if (tick == 7) {
            return getProductId();
        }else if (tick == 8) {
            return getSeries();
        }else if (tick == 9) {
            return getChipDimensionsLxW();
        }else if (tick == 10) {
            return getHeightDimensionT();
        }else if (tick == 11) {
            return getTemperatureCharacteristics();
        }else if (tick == 12) {
            return getRatedVoltageH();
        }else if (tick == 13) {
            return getCapacitance();
        }else if (tick == 14) {
            return getCapacitanceTolerance();
        }else if (tick == 15) {
            return getIndividualSpecificationCodeOrLLR();
        }else if (tick == 16) {
            return getPacking();
        } else {
            return "no column";
        }
    }


}
