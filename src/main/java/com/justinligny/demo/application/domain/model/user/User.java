package com.justinligny.demo.application.domain.model.user;

import com.justinligny.demo.application.domain.model.shared.valueobject.Email;
import com.justinligny.demo.application.domain.model.user.valueobject.FirstName;
import com.justinligny.demo.application.domain.model.user.valueobject.LastName;
import com.justinligny.demo.application.domain.model.user.valueobject.UserId;
import com.justinligny.demo.common.validation.Validation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@ToString
public final class User {

    @Valid
    private final UserId id;

    @NotNull(message = "First name cannot be null")
    @Valid
    private final FirstName firstName;

    @NotNull(message = "Last name cannot be null")
    @Valid
    private final LastName lastName;

    @NotNull(message = "Email cannot be null")
    @Valid
    private final Email email;

    private User(
            final UserId id,
            final FirstName firstName,
            final LastName lastName,
            final Email email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        Validation.validate(this);
    }

    public static User withoutId(
            final FirstName firstName,
            final LastName lastName,
            final Email email) {

        return new User(null, firstName, lastName, email);
    }

    public static User withId(
            final UserId id,
            final FirstName firstName,
            final LastName lastName,
            final Email email) {

        return new User(id, firstName, lastName, email);
    }

    public Optional<UserId> getId() {
        return Optional.ofNullable(this.id);
    }
}
