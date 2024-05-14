package com.example.elembase.repository;

import com.example.elembase.Entitity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    String filterQueryText = "";
    @Query(value = "select * from diplom.product where" +
            "    if(?1 != 'none', ?1, l) = l and" +
            "    if(?2 != 'none', ?2, w) = w and" +
            "    if(?3 != 'none', ?3, operating_temp_range) = operating_temp_range and" +
            "    if(?4 != 'none', ?4, rated_voltage_vdc) = rated_voltage_vdc and" +
            "    if(?5 != 'none', ?5, tc_code) = tc_code and" +
            "    if(?6 != 'none', ?6, cap) = cap and" +
            "    if(?7 != 'none', ?7, tol) = tol and" +
            "    if(?8 != 'none', ?8, product_id) = product_id and" +
            "    if(?9 != 'none', ?9, series) = series and" +
            "    if(?10 != 'none', ?10, chip_dimensions_lxw) = chip_dimensions_lxw and" +
            "    if(?11 != 'none', ?11, height_dimension_t) = height_dimension_t and" +
            "    if(?12 != 'none', ?12, temperature_characteristics) = temperature_characteristics and" +
            "    if(?13 != 'none', ?13, rated_voltage_h) = rated_voltage_h and" +
            "    if(?14 != 'none', ?14, capacitance) = capacitance and" +
            "    if(?15 != 'none', ?15, capacitance_tolerance) = capacitance_tolerance and" +
            "    if(?16 != 'none', ?16, individual_specification_code_or_llr) = individual_specification_code_or_llr and" +
            "    if(?17 != 'none', ?17, packing) = packing", nativeQuery = true)
    List<Product> getFilteredProducts(String l, String w, String operating_temp_range, String rated_voltage_vdc,
                                     String tc_code, String cap, String tol, String product_id, String series,
                                     String chip_dimensions_lxw, String height_dimension_t, String temperature_characteristics,
                                     String rated_voltage_h, String capacitance, String capacitance_tolerance,
                                     String individual_specification_code_or_llr, String packing);

    @Query(value = "select id from product WHERE " +
            "l = ?1 and w =?2 and operating_temp_range = ?3 and rated_voltage_vdc = ?4 and tc_code = ?5 and cap = ?6" +
            " and tol = ?7 and product_id = ?8 and series = ?9 and chip_dimensions_lxw = ?10", nativeQuery = true)
    List<Product> findAllByL(String l, String w);

    @Query(value = "select l from product", nativeQuery = true)
    List<String> getAllL();

    @Query(value = "select w from product", nativeQuery = true)
    List<String> getAllW();

    @Query(value = "select operating_temp_range from product", nativeQuery = true)
    List<String> getAllOperating_temp_range();

    @Query(value = "select rated_voltage_vdc from product", nativeQuery = true)
    List<String> getAllRated_voltage_vdc();

    @Query(value = "select tc_code from product", nativeQuery = true)
    List<String> getAllTc_code();

    @Query(value = "select cap from product", nativeQuery = true)
    List<String> getAllCap();

    @Query(value = "select tol from product", nativeQuery = true)
    List<String> getAllTol();

    @Query(value = "select * from product where id = ?1", nativeQuery = true)
    List<Product> getByiD(Long productId);


}
