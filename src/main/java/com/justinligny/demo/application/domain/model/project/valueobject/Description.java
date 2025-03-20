package com.justinligny.demo.application.domain.model.project.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@AllArgsConstructor
@ToString
public final class Description {

    @NotNull(message = "Description cannot be null")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private final String description;
}
