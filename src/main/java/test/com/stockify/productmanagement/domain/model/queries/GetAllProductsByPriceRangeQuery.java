package test.com.stockify.productmanagement.domain.model.queries;

public record GetAllProductsByPriceRangeQuery(Double minPrice, Double maxPrice) {
}
