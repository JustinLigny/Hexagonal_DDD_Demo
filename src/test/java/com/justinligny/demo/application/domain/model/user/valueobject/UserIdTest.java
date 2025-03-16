package com.justinligny.demo.application.domain.model.user.valueobject;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserIdTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private UserId systemUnderTest;

    @BeforeAll
    static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        validatorFactory.close();
    }

    @Test
    void shouldAcceptValidUserId() {
        systemUnderTest = new UserId(1L);

        Set<ConstraintViolation<UserId>> violations = validator.validate(systemUnderTest);

        assertTrue(violations.isEmpty(),
                "Expected no violations for valid UserId.");
    }

    @Test
    void shouldRejectNullUserId() {
        systemUnderTest = new UserId(null);

        Set<ConstraintViolation<UserId>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(),
                "Expected exactly one violation for null UserId.");
    }

    @Test
    void shouldRejectNegativeUserId() {
        systemUnderTest = new UserId(-1L);

        Set<ConstraintViolation<UserId>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(),
                "Expected exactly one violation for negative UserId.");
    }

    @Test
    void shouldRejectNullUserIdMessage() {
        systemUnderTest = new UserId(null);

        Set<ConstraintViolation<UserId>> violations = validator.validate(systemUnderTest);

        assertEquals("User's id cannot be null", violations.iterator().next().getMessage(),
                "Expected specific error message for null UserId.");
    }

    @Test
    void shouldRejectNegativeUserIdMessage() {
        systemUnderTest = new UserId(-1L);

        Set<ConstraintViolation<UserId>> violations = validator.validate(systemUnderTest);

        assertEquals("User's id cannot be negative", violations.iterator().next().getMessage(),
                "Expected specific error message for negative UserId.");
    }
}
