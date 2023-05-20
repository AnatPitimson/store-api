package webapi.restapplication.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import webapi.restapplication.controller.ProductController;
import webapi.restapplication.model.Product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component//הקוד יסרוק את הקוד הזה לפני שימוש והפעלה
public class ProductEntityAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    /*
    an assembler is a component responsible for converting
    domain objects into their corresponding representations as HTTP responses.
    In other words, an assembler takes a domain object and creates a representation of it
    that can be returned as an HTTP response. This can include additional metadata such as links
    to related resources, as well as other hypermedia controls.
     */

    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getSingleProduct(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("back to all products"));
    }


}
