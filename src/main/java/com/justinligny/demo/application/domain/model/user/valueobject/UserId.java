package com.justinligny.demo.application.domain.model.user.valueobject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public final class UserId {

    @NotNull(message = "User's id cannot be null")
    @PositiveOrZero(message = "User's id cannot be negative")
    private final Long id;
}
