package com.example.elembase.Entitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "status")
    private Long status;
}
