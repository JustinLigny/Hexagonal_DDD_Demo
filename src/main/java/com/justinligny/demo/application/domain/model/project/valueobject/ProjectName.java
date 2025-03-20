package com.justinligny.demo.application.domain.model.project.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public final class ProjectName {

    @NotNull(message = "Project name cannot be null")
    @Size(max = 40, message = "Project name cannot exceed 40 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Project name can only contain letters and spaces")
    private final String name;
}
