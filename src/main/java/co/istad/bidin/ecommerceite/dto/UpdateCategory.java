package co.istad.bidin.ecommerceite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCategory(
        @NotBlank(message = "name is required")
        @Size(min = 3, max = 50)
        String name,
        @NotBlank(message = "icon is required")
        String icon,
        @NotBlank(message = "description is required")
        @Size(min = 3, max = 255)
        String description
) {
}
