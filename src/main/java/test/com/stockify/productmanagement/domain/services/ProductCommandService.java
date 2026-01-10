package test.com.stockify.productmanagement.domain.services;

import test.com.stockify.productmanagement.domain.model.aggregates.Product;
import test.com.stockify.productmanagement.domain.model.commands.CreateProductCommand;
import test.com.stockify.productmanagement.domain.model.commands.DeleteProductCommand;
import test.com.stockify.productmanagement.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product> handle(UpdateProductCommand command);
    Long handle(CreateProductCommand command);
    void handle(DeleteProductCommand command);
}
