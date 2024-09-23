package com.cp.kku.housely.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_item")
@JsonIgnoreProperties("customerOrder")
public class OrderItem {
    @EmbeddedId
    private OrderItemKey orderItemId;
    private int quantity;


    // Relationship with CustomerOrder
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private CustomerOrder customerOrder;

    // Relationship with Product
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("orderItems")
    private Product product ;
}
