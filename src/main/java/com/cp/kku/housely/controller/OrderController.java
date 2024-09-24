package com.cp.kku.housely.controller;

import com.cp.kku.housely.model.CustomerOrder;
import com.cp.kku.housely.model.OrderItem;
import com.cp.kku.housely.model.OrderItemKey;
import com.cp.kku.housely.model.User;
import com.cp.kku.housely.service.OrderItemService;
import com.cp.kku.housely.service.OrderService;
import com.cp.kku.housely.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final UserService userService;

    public OrderController(OrderService orderService, OrderItemService orderItemService, UserService userService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.userService = userService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllOrders(@PathVariable Long userId) {
        try{
            userService.findById(userId);
            List<CustomerOrder> customerOrders = orderService.findCustomerOrdersByCustomerId(userId);
            return new ResponseEntity<>(customerOrders, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long userId, @PathVariable Long orderId) {
        try{
            userService.findById(userId);
            return new ResponseEntity<>(
                    orderService.findOrderByCustomerAndOrderId(userId, orderId),
                    HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/items/{userId}/{orderId}")
    public ResponseEntity<?> getItemsByOrderId(@PathVariable Long userId,@PathVariable Long orderId) {
        try {
            userService.findById(userId);
            CustomerOrder customerOrder = orderService.findById(orderId);
            return new ResponseEntity<>(customerOrder.getOrderItems(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/items/add")
    public ResponseEntity<?> createOrderItem(@RequestBody OrderItem orderItem) {
        try {
            User user = userService.findById(orderItem.getCustomerOrder().getUser().getId());
            CustomerOrder order = orderService.findById(orderItem.getOrderItemId().getOrderId());
            user.getCustomerOrders().add(order);
            order.setUser(user);
            OrderItem newOrderItem = orderItemService.save(orderItem);
            order.getOrderItems().add(newOrderItem);
            orderService.save(order);
            userService.save(user);
            return new ResponseEntity<>(newOrderItem, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createOrder(@RequestBody CustomerOrder order) {
        try {
            User user = userService.findById(order.getUser().getId());
            order.setUser(user);
            CustomerOrder newOrder = orderService.save(order);
            user.getCustomerOrders().add(newOrder);
            userService.save(user);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/edit/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody CustomerOrder order) {
        try {
            CustomerOrder oldOrder = orderService.findById(orderId);
            order.setOrderId(oldOrder.getOrderId());
            return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/items/{orderId}/{productId}")
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


    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        try {
            orderService.findById(orderId);
            orderService.deleteById(orderId);
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/items/{orderId}/{productId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long orderId, @PathVariable Long productId) {
        try {
            // ตรวจสอบว่ามี CustomerOrder ที่เกี่ยวข้องอยู่หรือไม่
            orderService.findById(orderId);

            // สร้าง OrderItemKey จาก orderId และ productId
            OrderItemKey orderItemId = new OrderItemKey();
            orderItemId.setOrderId(orderId);
            orderItemId.setProductId(productId);

            // ตรวจสอบว่ามี OrderItem ที่ต้องการลบหรือไม่
            orderItemService.findById(orderItemId);

            // ลบ OrderItem
            orderItemService.deleteById(orderItemId);

            return new ResponseEntity<>("OrderItem deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
