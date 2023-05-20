package webapi.restapplication.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapi.restapplication.assembler.ProductEntityAssembler;
import webapi.restapplication.model.Product;
import webapi.restapplication.service.ProductService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController//סוג של component
// כל פונקציה שכתובה פה מחזירה את הערך בHTTP Response כלומר את הערך הרלוונטי ולא מה שהמשתמש יראה browser
//יוחזר Json ולא View
//התוכנית תסרוק את המחלקה לפני שימוש והפעלה
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ProductEntityAssembler productEntityAssembler;

    public ProductController(ProductService productService, ProductEntityAssembler productEntityAssembler) {
        this.productEntityAssembler = productEntityAssembler;
        this.productService=productService;
    }

    /*    @GetMapping("/products")
    List<Product> getAllProducts(){
        return this.productRepo.findAll();
    }
 */
    /*@GetMapping("/allproducts")
    public CollectionModel<EntityModel<Product>> getAllProducts(){
        List<Product> productList=productRepo.findAll();
        //EntityModel
        // קונטיינר שיודע לקחת טיפוס קונקרטי לתת לנו את הייצוג שלו כJson ולהוסיף לו לינקים(hal+json)

        //CollectionModel
        //קונטיינר של קונטיינרים קולקשן של קונטיינרים
        List<EntityModel<Product>>entityList = productList.stream()
                .map(product -> productEntityAssembler.toModel(product)).collect(Collectors.toList());
        return CollectionModel.of(entityList,linkTo(methodOn(ProductController.class)
        .getAllProducts()).withSelfRel());
//        List<EntityModel<Product>>entityList = productList.stream().map(product -> EntityModel.of(product,
//                linkTo(methodOn(ProductController.class).getSingleProduct(product.getId())).withSelfRel(),
//                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("back to all products")))
//                .collect(Collectors.toList());
//       return CollectionModel.of(entityList,linkTo(methodOn(ProductController.class)
//                       .getAllProducts()).withSelfRel());

    }

     */

    @GetMapping
    public CollectionModel<EntityModel<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();

        List<EntityModel<Product>> entityList = productList.stream()
                .map(product -> productEntityAssembler.toModel(product)).collect(Collectors.toList());

        return CollectionModel.of(entityList, linkTo(methodOn(ProductController.class)
                .getAllProducts()).withSelfRel());

    }

    //כדי לשלוח Exceptions  ב SpringBoot צריך ליצור מחלקה חדשה
    @GetMapping("/getproduct")
    public EntityModel<Product> getProductParam(@RequestParam (required = true) Long id){
        return productEntityAssembler.toModel(productService.getProductById(id));
        //return productRepo.findById(id).orElseThrow(()->new ProductNotFoundException(id));
    }

    @GetMapping("/getproduct/{id}")
    public EntityModel<Product> getSingleProduct(@PathVariable Long id){
        return productEntityAssembler.toModel(productService.getProductById(id));
    }

    /*@GetMapping("/{id}")
    public EntityModel<Product> getSingleProduct(@PathVariable Long id){
        return productEntityAssembler.toModel(productRepo.findById(id).orElseThrow(()->
                new ProductNotFoundException(id)));
//        Product product=productRepo.findById(id).orElseThrow(()->
//                new ProductNotFoundException(id));
//        return productEntityAssembler.toModel(product);
//
//
//
//        return EntityModel.of(product,
//                linkTo(methodOn(ProductController.class).getSingleProduct(id)).withSelfRel(),
//                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("back to all products"));
//
    }
     */

//    @GetMapping("/productscategory")
//    List<Product> getProductsByCategory(@RequestParam (required = false) String category){
//        if(category!=null)
//            return productRepo.getProductsByCategory(category);
//        return getAllProducts();
//    }

    @GetMapping("/getbycategory/{category}")
    CollectionModel<EntityModel<Product>> getProductsByCategory(@PathVariable String category){

        List<Product> list= productService.getProductsByCategory(category);

        List<EntityModel<Product>> entityModel=list.stream().map(
                product -> productEntityAssembler.toModel(product)).collect(Collectors.toList());

        return CollectionModel.of(entityModel,linkTo(methodOn(ProductController.class)
                .getAllProducts()).withSelfRel());

    }


    @PostMapping("/createproduct")
    ResponseEntity<?> createProduct(@RequestBody Product newProduct){
        try {
            Product product=productService.createProduct(newProduct);
            EntityModel<Product> productEntity = productEntityAssembler.toModel(product);
            return ResponseEntity.created(new URI(productEntity.getRequiredLink(IanaLinkRelations.SELF)
            .getHref())).body(productEntity);
        } catch (URISyntaxException uriSyntaxException) {
            return ResponseEntity.badRequest().body("can NOT create a new product corresponding to "+newProduct);
        }

    }


    @PutMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product newProduct)
    {
        return ResponseEntity.ok(productService.updateProduct(id,newProduct));
    }

/*    @PostMapping("/createproduct")
    ResponseEntity<EntityModel<Product>> createNewProduct(@RequestBody Product newProduct) {
        //ResponseEntity מאפשר להתעסק עם הסטטוס קוד
        //בנוסף להוסיף מידע להדרים

        Product savesProduct=productRepo.save(newProduct);
        return ResponseEntity.created(linkTo(methodOn(ProductController.class)
                //created מוסיף שורה להדר של מיקום וסטטוס 201 של הוספה
                .getSingleProduct(savesProduct.getId())).toUri()).body(productEntityAssembler.toModel(savesProduct));
                //toUri מחפש את
                //_links
                //ואז מחפש את self ref

    }

 */
/*
    //safeCreateProduct
    @PostMapping("/createproduct")
    ResponseEntity<?> safeProductCreation(@RequestBody Product newProduct) {
        // the ? is a wildcard (any kind of type) that represents an object of some type (Product or false object)

        try {
            Product product=productService.createProduct(newProduct);
            EntityModel<Product> productEntity = productEntityAssembler.toModel(product);

            //same idea different way
            return ResponseEntity.created(new URI(productEntity.getRequiredLink(IanaLinkRelations.SELF)
                    .getHref())).body(productEntity);

        } catch (URISyntaxException uriException) {
            return ResponseEntity.badRequest().body("can NOT create a new product corresponding to "+newProduct);
        }

    }
*/

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
