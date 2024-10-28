package com.store.service;

import com.store.model.Order;

public interface OrderService {

    Order saveOrder(Order order);

    Order findOrderById(Long id);

    Order updateOrderStatus(Long id, Integer status);
}
