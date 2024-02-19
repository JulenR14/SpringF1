package org.api.springf1.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ConstructorResponse(
        List<ConstructorDTO> content,
        int pageNo,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
) {
}
