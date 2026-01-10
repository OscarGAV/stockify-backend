package test.com.stockify.productmanagement.application.internal;

import org.springframework.stereotype.Service;
import test.com.stockify.productmanagement.domain.model.aggregates.Product;
import test.com.stockify.productmanagement.domain.model.queries.GetAllProductsByCategoryQuery;
import test.com.stockify.productmanagement.domain.model.queries.GetAllProductsByPriceRangeQuery;
import test.com.stockify.productmanagement.domain.model.queries.GetAllProductsQuery;
import test.com.stockify.productmanagement.domain.model.queries.GetProductByIdQuery;
import test.com.stockify.productmanagement.domain.services.ProductQueryService;
import test.com.stockify.productmanagement.infrastructure.persistence.jpa.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> handle(GetAllProductsByCategoryQuery query) {
        return productRepository.findAllByCategory(query.category());
    }

    @Override
    public List<Product> handle(GetAllProductsByPriceRangeQuery query) {
        return productRepository.findAllByPriceBetween(query.minPrice(), query.maxPrice());
    }

    @Override
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.id());
    }
}
