package com.justinligny.demo.application.domain.model.project.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@AllArgsConstructor
@ToString
public final class ProjectReference {

    @NotNull(message = "Project reference cannot be blank")
    @Pattern(regexp = "^[A-Z0-9]{8}$", message = "Project reference must be 8 characters long, using only A-Z and 0-9")
    private final String reference;
}
