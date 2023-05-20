package webapi.restapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data//getters setters
@Entity//to save in data base
@NoArgsConstructor
@Table(name = "products")
public class Product {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;//to save in data base
    private String productName;
    private String category;
    private double price;
    private long quantity;

    //@ManyToOne private OrderProduct order;


    public Product(String productName,String category,double price,long quantity){
        this.productName=productName;
        this.category=category;
        this.price=price;
        this.quantity=quantity;

    }

}
