package test.com.stockify.productmanagement.interfaces.rest.transform;

import test.com.stockify.productmanagement.domain.model.commands.UpdateProductCommand;
import test.com.stockify.productmanagement.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommandFromResource(Long productId, UpdateProductResource resource) {
        return new UpdateProductCommand(
                productId,
                resource.name(),
                resource.description(),
                resource.price(),
                resource.stockQuantity(),
                resource.category());
    }
}
