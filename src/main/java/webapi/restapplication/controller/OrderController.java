package webapi.restapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapi.restapplication.assembler.OrderEntityAssembler;
import webapi.restapplication.model.OrderProduct;
import webapi.restapplication.service.OrderService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    private final OrderEntityAssembler orderEntityAssembler;

    public OrderController(OrderService orderService,OrderEntityAssembler orderEntityAssembler) {
        this.orderEntityAssembler=orderEntityAssembler;
        this.orderService = orderService;
    }

    @GetMapping
    public CollectionModel<EntityModel<OrderProduct>> getAllOrders() {
        List<OrderProduct> list=orderService.getAllOrders();

        List<EntityModel<OrderProduct>> entityModels=list.stream()
                .map(orderProduct -> orderEntityAssembler.toModel(orderProduct)).collect(Collectors.toList());
        return CollectionModel.of(entityModels,linkTo(methodOn(OrderController.class)
        .getAllOrders()).withSelfRel());

    }

    @GetMapping("/getorder/{id}")
    public EntityModel<OrderProduct> getOrderById(@PathVariable Long id) {
        return orderEntityAssembler.toModel(orderService.getOrderById(id));
    }

    @PostMapping("/createorder")
    ResponseEntity<?> createOrder(@RequestBody OrderProduct order) {
        OrderProduct orderProduct= orderService.createOrder(order);
        EntityModel<OrderProduct> entityModel = orderEntityAssembler.toModel(orderProduct);
        try {
            return ResponseEntity.created(new URI(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                    .getHref())).body(entityModel);
        } catch (URISyntaxException uriSyntaxException) {
            return ResponseEntity.badRequest().body("can NOT create a new product corresponding to "+entityModel);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderProduct> updateOrder(@PathVariable Long id, @RequestBody OrderProduct orderDetails) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDetails));
    }

    @DeleteMapping("/deleteorder/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
