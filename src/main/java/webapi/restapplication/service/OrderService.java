package webapi.restapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapi.restapplication.exception.OrderNotFoundException;
import webapi.restapplication.model.OrderProduct;
import webapi.restapplication.model.Product;
import webapi.restapplication.repository.OrderRepo;
import webapi.restapplication.repository.OrderServiceInterface;

import java.util.List;

@Service
public class OrderService implements OrderServiceInterface {

    private OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<OrderProduct> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public OrderProduct getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public OrderProduct createOrder(OrderProduct order) {
        order.setTotalPrice(calcTotalPrice(order));
        return orderRepo.save(order);
    }

    @Override
    public OrderProduct updateOrder(Long id, OrderProduct orderDetails) {
        OrderProduct order = getOrderById(id);
        //order.setStatus(orderDetails.getStatus());
        order.setOrderProducts(orderDetails.getOrderProducts());

        order.setTotalPrice(calcTotalPrice(orderDetails));

        return orderRepo.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        OrderProduct orderProduct=orderRepo.findById(id).orElseThrow(()->new OrderNotFoundException(id));
        orderRepo.delete(orderProduct);
    }


    public double calcTotalPrice(OrderProduct orderProduct)
    {
        double totalPrice=0;
        for(Product product:orderProduct.getOrderProducts()) {
            totalPrice += product.getQuantity() * product.getPrice();
        }
        orderProduct.setTotalPrice(totalPrice);
        return totalPrice;
        //totalPrice=orderProducts.stream().mapToDouble(op -> op.getPrice() * op.getQuantity()).sum();
    }




}
