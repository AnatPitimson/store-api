package webapi.restapplication.repository;

import webapi.restapplication.model.OrderProduct;

import java.util.List;

public interface OrderServiceInterface {

    List<OrderProduct> getAllOrders();
    OrderProduct getOrderById(Long id);
    OrderProduct createOrder(OrderProduct order);
    OrderProduct updateOrder(Long id, OrderProduct order);
    void deleteOrder(Long id);

}
