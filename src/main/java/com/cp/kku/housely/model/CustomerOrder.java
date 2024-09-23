package com.cp.kku.housely.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "customer_order")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
    private String paymentStatus;
    private double totalAmount;

    // Relationship with OrderItem
    @JsonIgnoreProperties("customerOrder")
    @OneToMany(mappedBy = "customerOrder",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();
}
