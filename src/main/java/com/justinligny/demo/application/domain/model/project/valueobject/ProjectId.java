package com.justinligny.demo.application.domain.model.project.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public final class ProjectId {

    @NotNull(message = "Project's id cannot be null")
    @PositiveOrZero(message = "Project's id cannot be negative")
    private final Long id;
}
