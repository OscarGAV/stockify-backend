package test.com.stockify.productmanagement.interfaces.rest.resources;

public record CreateProductResource(String name,
                                    String description,
                                    Double price,
                                    Integer stockQuantity,
                                    String category) {
}
