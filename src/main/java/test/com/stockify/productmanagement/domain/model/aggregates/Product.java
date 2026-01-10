package test.com.stockify.productmanagement.domain.model.aggregates;

import jakarta.persistence.Entity;
import lombok.Getter;
import test.com.stockify.productmanagement.domain.model.commands.CreateProductCommand;
import test.com.stockify.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class Product extends AuditableAbstractAggregateRoot<Product> {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String category;

    public Product() {
        this.name = "";
        this.description = "";
        this.price = 0.0;
        this.stockQuantity = 0;
        this.category = "";
    }

    public Product updateInformation(String name, String description, Double price, Integer stockQuantity, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        return this;
    }

    public Product(CreateProductCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();
        this.stockQuantity = command.stockQuantity();
        this.category = command.category();
    }
}
