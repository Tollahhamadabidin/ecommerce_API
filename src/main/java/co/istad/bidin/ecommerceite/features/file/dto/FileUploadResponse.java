package co.istad.bidin.ecommerceite.features.file.dto;

import lombok.Builder;

@Builder
public record FileUploadResponse(
        String name,
        String caption,
        Long size,
        String mediaType,
        String uri
) {
}
