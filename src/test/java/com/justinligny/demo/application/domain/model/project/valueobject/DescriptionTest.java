package com.justinligny.demo.application.domain.model.project.valueobject;

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

class DescriptionTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private Description systemUnderTest;

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
    void shouldAcceptValidDescription() {
        systemUnderTest = new Description("This is a valid project description.");

        Set<ConstraintViolation<Description>> violations = validator.validate(systemUnderTest);

        assertTrue(violations.isEmpty(), "Expected no violations for a valid description.");
    }

    @Test
    void shouldRejectNullDescription() {
        systemUnderTest = new Description(null);

        Set<ConstraintViolation<Description>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for null description.");
    }

    @Test
    void shouldRejectTooLongDescription() {
        systemUnderTest = new Description("A".repeat(501));

        Set<ConstraintViolation<Description>> violations = validator.validate(systemUnderTest);

        assertEquals(1, violations.size(), "Expected exactly one violation for too long description.");
    }

    @Test
    void shouldRejectNullDescriptionMessage() {
        systemUnderTest = new Description(null);

        Set<ConstraintViolation<Description>> violations = validator.validate(systemUnderTest);

        assertEquals("Description cannot be null", violations.iterator().next().getMessage(),
                "Expected specific error message for null description.");
    }

    @Test
    void shouldRejectTooLongDescriptionMessage() {
        systemUnderTest = new Description("A".repeat(501));

        Set<ConstraintViolation<Description>> violations = validator.validate(systemUnderTest);

        assertEquals("Description cannot exceed 500 characters", violations.iterator().next().getMessage(),
                "Expected specific error message for too long description.");
    }
}
