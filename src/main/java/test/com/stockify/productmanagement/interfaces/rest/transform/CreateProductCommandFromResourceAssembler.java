package test.com.stockify.productmanagement.interfaces.rest.transform;

import test.com.stockify.productmanagement.domain.model.commands.CreateProductCommand;
import test.com.stockify.productmanagement.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        return new CreateProductCommand(
                resource.name(),
                resource.description(),
                resource.price(),
                resource.stockQuantity(),
                resource.category());
    }
}
