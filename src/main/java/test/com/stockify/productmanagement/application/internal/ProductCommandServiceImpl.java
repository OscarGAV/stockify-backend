package test.com.stockify.productmanagement.application.internal;

import org.springframework.stereotype.Service;
import test.com.stockify.productmanagement.domain.model.aggregates.Product;
import test.com.stockify.productmanagement.domain.model.commands.CreateProductCommand;
import test.com.stockify.productmanagement.domain.model.commands.DeleteProductCommand;
import test.com.stockify.productmanagement.domain.model.commands.UpdateProductCommand;
import test.com.stockify.productmanagement.domain.services.ProductCommandService;
import test.com.stockify.productmanagement.infrastructure.persistence.jpa.repositories.ProductRepository;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private  final ProductRepository productRepository;
    public ProductCommandServiceImpl(ProductRepository ProductRepository) {
        this.productRepository = ProductRepository;
    }

    @Override
    public Long handle(CreateProductCommand command) {
        var Product = new Product(command);
        try {
            productRepository.save(Product);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving Product: " + e.getMessage());
        }
        return Product.getId();
    }

    @Override
    public Optional<Product> handle(UpdateProductCommand command) {
        if (!productRepository.existsById(command.id())) throw new IllegalArgumentException("ProductId does not exist");
        var result = productRepository.findById(command.id());
        var ProductToUpdate = result.get();
        try {
            var updatedProduct = productRepository.save(ProductToUpdate.updateInformation(command.name(),
                    command.description(),
                    command.price(),
                    command.stockQuantity(),
                    command.category()));
            return Optional.of(updatedProduct);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Product: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteProductCommand command) {
        if (!productRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Pe does not exist");
        }
        try {
            productRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting product: " + e.getMessage());
        }
    }
}
