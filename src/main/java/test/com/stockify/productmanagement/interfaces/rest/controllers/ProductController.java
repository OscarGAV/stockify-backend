package test.com.stockify.productmanagement.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.com.stockify.productmanagement.domain.model.commands.DeleteProductCommand;
import test.com.stockify.productmanagement.domain.model.queries.GetAllProductsByCategoryQuery;
import test.com.stockify.productmanagement.domain.model.queries.GetAllProductsByPriceRangeQuery;
import test.com.stockify.productmanagement.domain.model.queries.GetAllProductsQuery;
import test.com.stockify.productmanagement.domain.model.queries.GetProductByIdQuery;
import test.com.stockify.productmanagement.domain.services.ProductCommandService;
import test.com.stockify.productmanagement.domain.services.ProductQueryService;
import test.com.stockify.productmanagement.interfaces.rest.resources.CreateProductResource;
import test.com.stockify.productmanagement.interfaces.rest.resources.ProductResource;
import test.com.stockify.productmanagement.interfaces.rest.resources.UpdateProductResource;
import test.com.stockify.productmanagement.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import test.com.stockify.productmanagement.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import test.com.stockify.productmanagement.interfaces.rest.transform.UpdateProductCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Products Management Endpoints")
@CrossOrigin(origins = {"https://stockify-web-front.netlify.app","http://localhost:4200"})
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductController(ProductCommandService productCommandService,
                             ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long productId) {
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()) return ResponseEntity.badRequest().build();
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<ProductResource>> getAllProductByCategory(@PathVariable String category) {
        var getProductByCategoryQuery = new GetAllProductsByCategoryQuery(category);
        var products = productQueryService.handle(getProductByCategoryQuery);
        var productResources = products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productResources);
    }

    @GetMapping("/price-range/{minPrice}/{maxPrice}")
    public ResponseEntity<List<ProductResource>> getAllProductByPriceRange(@PathVariable Double minPrice, @PathVariable Double maxPrice) {
        var getProductByPriceRangeQuery = new GetAllProductsByPriceRangeQuery(minPrice, maxPrice);
        var products = productQueryService.handle(getProductByPriceRangeQuery);
        var productResources = products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productResources);
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        var getAllProductsQuery = new GetAllProductsQuery();
        var products = productQueryService.handle(getAllProductsQuery);
        var productResources = products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productResources);
    }

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource createProductResource) {
        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(createProductResource);
        var productId = productCommandService.handle(createProductCommand);
        if (productId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()) return ResponseEntity.badRequest().build();
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductResource updateProductResource) {
        var updateProductCommand = UpdateProductCommandFromResourceAssembler.toCommandFromResource(productId, updateProductResource);
        var updatedProduct = productCommandService.handle(updateProductCommand);
        if (updatedProduct.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(updatedProduct.get());
        return ResponseEntity.ok(productResource);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        var deleteProductCommand = new DeleteProductCommand(productId);
        productCommandService.handle(deleteProductCommand);
        return ResponseEntity.ok("Product with given id successfully deleted");
    }
}