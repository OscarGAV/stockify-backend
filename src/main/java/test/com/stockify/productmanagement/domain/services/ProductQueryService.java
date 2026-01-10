package test.com.stockify.productmanagement.domain.services;

import test.com.stockify.productmanagement.domain.model.aggregates.Product;
import test.com.stockify.productmanagement.domain.model.queries.GetAllProductsByCategoryQuery;
import test.com.stockify.productmanagement.domain.model.queries.GetAllProductsByPriceRangeQuery;
import test.com.stockify.productmanagement.domain.model.queries.GetAllProductsQuery;
import test.com.stockify.productmanagement.domain.model.queries.GetProductByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    List<Product> handle(GetAllProductsByCategoryQuery query);
    List<Product> handle(GetAllProductsByPriceRangeQuery query);
    List<Product> handle(GetAllProductsQuery query);
    Optional<Product> handle(GetProductByIdQuery query);
}
