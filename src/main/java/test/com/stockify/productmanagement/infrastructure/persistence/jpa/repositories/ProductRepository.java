package test.com.stockify.productmanagement.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.com.stockify.productmanagement.domain.model.aggregates.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsById(Long id);
    List<Product> findAllByCategory(String category);
    List<Product> findAllByPriceBetween(Double minPrice, Double maxPrice);
}
