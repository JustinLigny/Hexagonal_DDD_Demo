package com.justinligny.demo.application.domain.model.user.valueobject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public final class FirstName {

    @NotBlank(message = "First name cannot be null or blank")
    @Size(max = 40, message = "First name cannot exceed 40 characters")
    private final String firstName;
}
