package webapi.restapplication.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import webapi.restapplication.controller.OrderController;
import webapi.restapplication.controller.ProductController;
import webapi.restapplication.model.OrderProduct;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderEntityAssembler implements RepresentationModelAssembler<OrderProduct, EntityModel<OrderProduct>> {


    @Override
    public EntityModel<OrderProduct> toModel(OrderProduct product) {
            return EntityModel.of(product,
            linkTo(methodOn(OrderController.class).getOrderById(product.getId())).withSelfRel(),
            linkTo(methodOn(ProductController.class).getAllProducts()).withRel("back to all products"));
            }
}
