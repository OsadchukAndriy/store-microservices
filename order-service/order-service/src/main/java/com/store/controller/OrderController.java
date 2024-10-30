package com.store.controller;

import com.store.model.Order;
import com.store.service.OrderProducer;
import com.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private OrderService orderService;

    @PostMapping()
    public Order createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);

        // send events to kafka
        orderProducer.sendOrderCreatedEvent(savedOrder.toString());

        return savedOrder;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PutMapping("/{id}/")
    public Order updateOrderStatus(@PathVariable Long id, Integer status) {
        return orderService.updateOrderStatus(id, status);
    }
}
