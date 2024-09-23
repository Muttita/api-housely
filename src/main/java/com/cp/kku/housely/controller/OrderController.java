package com.cp.kku.housely.controller;

import com.cp.kku.housely.model.CustomerOrder;
import com.cp.kku.housely.model.OrderItem;
import com.cp.kku.housely.model.OrderItemKey;
import com.cp.kku.housely.service.OrderItemService;
import com.cp.kku.housely.service.OrderService;
import com.cp.kku.housely.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final ProductService productService;

    public OrderController(OrderService orderService, OrderItemService orderItemService, ProductService productService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        try {
            return new ResponseEntity<>(orderService.findById(orderId), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<?> getOrderItemsByOrderId(@PathVariable Long orderId) {
        try {
            CustomerOrder customerOrder = orderService.findById(orderId);
            return new ResponseEntity<>(customerOrder.getOrderItems(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/items")
    public ResponseEntity<?> createOrderItem( @RequestBody OrderItem orderItem) {
        try {
            CustomerOrder order = orderService.findById(orderItem.getOrderItemId().getOrderId());
            OrderItem newOrderItem = orderItemService.save(orderItem);
            order.getOrderItems().add(newOrderItem);
            return new ResponseEntity<>(newOrderItem, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CustomerOrder order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody CustomerOrder order) {
        try {
            CustomerOrder oldOrder = orderService.findById(orderId);
            order.setOrderId(oldOrder.getOrderId());
            return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/items/{orderId}/{productId}")
    public ResponseEntity<?> updateOrderItem(@PathVariable Long orderId, @PathVariable Long productId, @RequestBody OrderItem orderItem) {
        try {
            CustomerOrder customerOrder = orderService.findById(orderId);
            orderItem.setCustomerOrder(customerOrder);

            OrderItemKey orderItemId = new OrderItemKey();
            orderItemId.setOrderId(orderId);
            orderItemId.setProductId(productId);

            OrderItem oldOrderItem = orderItemService.findById(orderItemId);

            orderItem.setOrderItemId(oldOrderItem.getOrderItemId());

            OrderItem updatedOrderItem = orderItemService.save(orderItem);

            return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        try {
            orderService.findById(orderId);
            orderService.deleteById(orderId);
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderId}/items/{orderItemId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long orderId, @PathVariable OrderItemKey orderItemId) {
        try {
            orderService.findById(orderId);
            orderItemService.findById(orderItemId);
            orderItemService.deleteById(orderItemId);
            return new ResponseEntity<>("OrderItem deleted successfully", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



}
