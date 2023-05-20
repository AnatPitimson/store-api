package webapi.restapplication.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data//getters setters
@Entity//to save in data base
@NoArgsConstructor//generates a no-argument constructor for the class.
@Table(name = "orders")
public class OrderProduct {

    private @Id// primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//generated automatically by the database
    Long id;

    private double totalPrice;

    @ManyToMany
    private List<Product> orderProducts;
    //@OneToMany(mappedBy = "order")
    //private List<Product> orderProducts;

    public OrderProduct(List<Product> orderProducts, double totalPrice) {
        this.orderProducts = new ArrayList<>();
        this.totalPrice = totalPrice;
    }



}
