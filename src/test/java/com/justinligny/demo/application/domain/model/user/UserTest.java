package com.justinligny.demo.application.domain.model.user;

import com.justinligny.demo.application.domain.model.shared.valueobject.Email;
import com.justinligny.demo.application.domain.model.user.valueobject.FirstName;
import com.justinligny.demo.application.domain.model.user.valueobject.LastName;
import com.justinligny.demo.application.domain.model.user.valueobject.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private UserId userId;
    private FirstName firstName;
    private LastName lastName;
    private Email email;

    @BeforeEach
    void setUp() {
        userId = new UserId(1L);
        firstName = new FirstName("John");
        lastName = new LastName("Doe");
        email = new Email("john.doe@example.com");
    }

    // ---- Tests for User.withId() ----

    @Test
    void shouldCreateUserWithId() {
        User user = User.withId(userId, firstName, lastName, email);

        assertNotNull(user);
    }

    @Test
    void userWithIdShouldHaveNonNullFirstName() {
        User user = User.withId(userId, firstName, lastName, email);

        assertNotNull(user.getFirstName());
    }

    @Test
    void userWithIdShouldHaveNonNullLastName() {
        User user = User.withId(userId, firstName, lastName, email);

        assertNotNull(user.getLastName());
    }

    @Test
    void userWithIdShouldHaveNonNullEmail() {
        User user = User.withId(userId, firstName, lastName, email);

        assertNotNull(user.getEmail());
    }

    @Test
    void userWithIdShouldHavePresentId() {
        User user = User.withId(userId, firstName, lastName, email);

        assertTrue(user.getId().isPresent());
    }

    @Test
    void userWithIdShouldReturnCorrectId() {
        User user = User.withId(userId, firstName, lastName, email);

        assertTrue(user.getId().isPresent());
        assertEquals(userId, user.getId().get());
    }

    // ---- Tests for User.withoutId() ----

    @Test
    void shouldCreateUserWithoutId() {
        User user = User.withoutId(firstName, lastName, email);
        assertNotNull(user);
    }

    @Test
    void userWithoutIdShouldHaveNonNullFirstName() {
        User user = User.withoutId(firstName, lastName, email);

        assertNotNull(user.getFirstName());
    }

    @Test
    void userWithoutIdShouldHaveNonNullLastName() {
        User user = User.withoutId(firstName, lastName, email);

        assertNotNull(user.getLastName());
    }

    @Test
    void userWithoutIdShouldHaveNonNullEmail() {
        User user = User.withoutId(firstName, lastName, email);

        assertNotNull(user.getEmail());
    }

    @Test
    void userWithoutIdShouldHaveEmptyOptionalId() {
        User user = User.withoutId(firstName, lastName, email);

        assertTrue(user.getId().isEmpty());
    }

    // ---- Tests for getId() ----

    @Test
    void shouldReturnOptionalIdWhenUserHasId() {
        User user = User.withId(userId, firstName, lastName, email);

        assertTrue(user.getId().isPresent());

        assertEquals(userId, user.getId().get());
    }

    @Test
    void shouldReturnEmptyOptionalIdWhenUserHasNoId() {
        User user = User.withoutId(firstName, lastName, email);

        assertTrue(user.getId().isEmpty());
    }
}
