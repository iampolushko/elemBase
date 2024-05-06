package com.example.elembase.repository;

import com.example.elembase.Entitity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

//    @Query(value = "select * from product WHERE l = ?1 and w =?2", nativeQuery = true)
//    List<Product> findAllByL(String l, String w);

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

}
