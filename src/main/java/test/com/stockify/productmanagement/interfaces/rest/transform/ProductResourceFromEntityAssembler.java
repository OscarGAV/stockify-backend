package test.com.stockify.productmanagement.interfaces.rest.transform;

import test.com.stockify.productmanagement.domain.model.aggregates.Product;
import test.com.stockify.productmanagement.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity) {
        return new ProductResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStockQuantity(),
                entity.getCategory());
    }
}
