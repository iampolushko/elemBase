package com.example.elembase.Entitity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


}
