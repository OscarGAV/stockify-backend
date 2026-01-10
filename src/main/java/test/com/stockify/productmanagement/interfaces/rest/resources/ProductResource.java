package test.com.stockify.productmanagement.interfaces.rest.resources;

public record ProductResource(Long id,
                              String name,
                              String description,
                              Double price,
                              Integer stockQuantity,
                              String category) {
}
