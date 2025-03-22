package com.justinligny.demo.application.domain.model.user.valueobject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public final class LastName {

    @NotBlank(message = "Last name cannot be null or blank")
    @Size(max = 40, message = "Last name cannot exceed 40 characters")
    private final String lastName;
}
