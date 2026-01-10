package test.com.stockify.productmanagement.domain.model.commands;

public record CreateProductCommand(String name,
                                   String description,
                                   Double price,
                                   Integer stockQuantity,
                                   String category) {
}
