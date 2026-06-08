package co.istad.bidin.ecommerceite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record CreateCategoryRequest(

        @NotBlank(message = "name is required")
        @Size(min = 3, max = 50)
        String name,
        @NotBlank(message = "description is required")
        @Size(min = 3, max = 255)
        String description,
        @NotBlank(message = "icon is required")
//        @URL(message = "icon must be a valid the URL")
        String icon,
        @Positive
        Integer parentCategoryId
) {
}
