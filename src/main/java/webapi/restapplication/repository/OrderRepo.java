package webapi.restapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapi.restapplication.model.OrderProduct;

@Repository//responsible for data access operations on the OrderProduct entity
public interface OrderRepo extends JpaRepository<OrderProduct,Long> {

}

