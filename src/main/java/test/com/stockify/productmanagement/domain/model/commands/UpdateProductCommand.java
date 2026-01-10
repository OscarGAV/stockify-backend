package test.com.stockify.productmanagement.domain.model.commands;

public record UpdateProductCommand(Long id,
                                   String name,
                                   String description,
                                   Double price,
                                   Integer stockQuantity,
                                   String category) {
}
